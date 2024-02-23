package com.demo.preorder.product.service.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.product.client.dto.OrderDto;
import com.demo.preorder.product.client.dto.OrderResponseDto;
import com.demo.preorder.product.client.service.OrderServiceClient;
import com.demo.preorder.product.dao.ProductDao;
import com.demo.preorder.product.dao.ProductStockDao;
import com.demo.preorder.product.dto.ProductDto;
import com.demo.preorder.product.dto.ProductResponseDto;
import com.demo.preorder.product.dto.ProductUpdateDto;
import com.demo.preorder.product.entity.Product;
import com.demo.preorder.product.entity.ProductStock;
import com.demo.preorder.product.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final ProductStockDao productStockDao;

    private final OrderServiceClient orderServiceClient;

    @Transactional
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setContent(productDto.getContent());
        product.setPrice(productDto.getPrice());
        Product savedProduct = productDao.saveProduct(product);

        if (savedProduct != null) {
            ProductStock productStock = new ProductStock();
            productStock.setProductId(savedProduct);
            productStock.setStock(productDto.getStock());
            productStockDao.saveProductStock(productStock);
        }
        assert savedProduct != null;
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

            ProductStock savedProductStock = productStockDao.getProductById(productId);
            if (savedProductStock != null && savedProductStock.getStock() > 0) {
                ProductStock productStock = productStockDao.decrementCount(productId);
                if (productStock.getStock() >= 0) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<OrderResponseDto> responseDtoResponseEntity = orderServiceClient.saveOrder(orderDto);
                        OrderResponseDto result = responseDtoResponseEntity.getBody();
                        log.info("Info log: order - productId ={} result={}", result.getProductId(), result.getStatus());
                        return result;
                    } catch (Exception e) {
                        log.error("Error order : {}", e.getMessage(), e);
                        return null;
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

    @Transactional
    @Override
    public ProductResponseDto changeProduct(Long productId, ProductUpdateDto productUpdateDto) {
        productStockDao.updateProductStock(productId, productUpdateDto.getStock());
        return new ProductResponseDto(productDao.changeProduct(productId, productUpdateDto.getTitle(), productUpdateDto.getContent(), productUpdateDto.getPrice()));
    }

    @Transactional
    @Override
    public void deleteProduct(Long productId) {
        productStockDao.deleteProductStock(productId);
        productDao.deleteProduct(productId);
    }
}
