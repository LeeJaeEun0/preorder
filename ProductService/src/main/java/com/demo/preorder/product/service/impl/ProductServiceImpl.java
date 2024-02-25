package com.demo.preorder.product.service.impl;

import com.demo.preorder.client.dto.OrderDto;
import com.demo.preorder.client.dto.OrderResponseDto;
import com.demo.preorder.client.dto.ProductStockDto;
import com.demo.preorder.client.service.OrderServiceClient;
import com.demo.preorder.client.service.StockServiceClient;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.product.dao.ProductDao;
import com.demo.preorder.product.dto.ProductDto;
import com.demo.preorder.product.dto.ProductResponseDto;
import com.demo.preorder.product.entity.Product;
import com.demo.preorder.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final StockServiceClient stockServiceClient;

    private final OrderServiceClient orderServiceClient;

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setContent(productDto.getContent());
        product.setPrice(productDto.getPrice());
        Product savedProduct = productDao.saveProduct(product);

        if (savedProduct != null) {
            ProductStockDto productStock = new ProductStockDto();
            productStock.setProductId(savedProduct.getId());
            productStock.setStock(productDto.getStock());

            try {
                // 외부 서비스 호출
                ResponseEntity<Long> productStocks = stockServiceClient.saveProductStocks(productStock);
                Long result = productStocks.getBody();
                log.info("Info log: productStock - {} ", result);
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", productStock.getProductId(), e.getMessage());
                throw e;
            } catch (Exception e) {
                log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", productStock.getProductId(), e.getMessage(), e);
                throw e;
            }

        }

        return new ProductResponseDto(savedProduct);
    }

    @Override
    public OrderResponseDto submitOrder(Long userId, Long productId) {
        Product product = productDao.getProductById(productId);
        if (product != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setUserId(userId);
            orderDto.setProductId(productId);
            orderDto.setProductType("product");
            orderDto.setCount(1L);
            orderDto.setTotalAmount(product.getPrice());

            Long productStock = 0L;
            try {
                // 외부 서비스 호출
                ResponseEntity<Long> productStocks = stockServiceClient.getProductStock(productId);
                productStock = productStocks.getBody();
                log.info("Info log: productStock - {} ", productStock);
            }  catch (HttpClientErrorException | HttpServerErrorException e) {
                log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage());
                throw e;
            } catch (Exception e) {
                log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage(), e);
                throw e;
            }
            if (productStock > 0) {
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = stockServiceClient.decrementProductStocks(productId);
                    productStock = productStocks.getBody();
                    log.info("Info log: productStock - {} ", productStock);
                } catch (HttpClientErrorException | HttpServerErrorException e) {
                    log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage());
                    throw e;
                } catch (Exception e) {
                    log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage(), e);
                    throw e;
                }
                if (productStock >= 0) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<OrderResponseDto> responseDtoResponseEntity = orderServiceClient.saveOrder(orderDto);
                        OrderResponseDto result = responseDtoResponseEntity.getBody();
                        log.info("Info log: order - productId ={} result={}", result.getProductId(), result.getStatus());
                        return result;
                    } catch (HttpClientErrorException | HttpServerErrorException e) {
                        log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage());
                        throw e;
                    } catch (Exception e) {
                        log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage(), e);
                        throw e;
                    }
                }
            }
        }
        else throw new CustomException(ErrorCode.INVALID_PRODUCT);
        return null;
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        return new ProductResponseDto(productDao.getProductById(productId));
    }

    @Override
    public List<ProductResponseDto> findAllProduct() {
        List<Product> products = productDao.findAllProduct();

        List<ProductResponseDto> productResponseDtos = products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());

        return productResponseDtos;
    }



    @Override
    public void deleteProduct(Long productId) {
        try {
            // 외부 서비스 호출
            ResponseEntity<Void> response = stockServiceClient.deleteProductStocks(productId);
            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("Info log: delete stock");
            } else {
                log.error("Info log: fail delete stock");
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", productId, e.getMessage(), e);
            throw e;
        }
        productDao.deleteProduct(productId);
    }
}
