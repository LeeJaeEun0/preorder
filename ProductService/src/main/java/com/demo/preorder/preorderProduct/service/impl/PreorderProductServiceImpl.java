package com.demo.preorder.preorderProduct.service.impl;

import com.demo.preorder.client.dto.OrderDto;
import com.demo.preorder.client.dto.OrderResponseDto;
import com.demo.preorder.client.dto.PreorderProductStockDto;
import com.demo.preorder.client.service.OrderServiceClient;
import com.demo.preorder.client.service.StockServiceClient;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.preorderProduct.dao.PreorderProductDao;
import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductResponseDto;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.preorderProduct.service.PreorderProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PreorderProductServiceImpl implements PreorderProductService {

    private final PreorderProductDao preorderProductDao;

    private final OrderServiceClient orderServiceClient;

    private final StockServiceClient stockServiceClient;

    @Override
    public PreorderProductResponseDto savePreorderProduct(PreorderProductDto preorderProductDto) {
        PreorderProduct preorderProduct = new PreorderProduct();
        preorderProduct.setTitle(preorderProductDto.getTitle());
        preorderProduct.setContent(preorderProductDto.getContent());
        preorderProduct.setPrice(preorderProductDto.getPrice());
        preorderProduct.setAvailableFrom(preorderProductDto.getAvailableFrom());
        PreorderProduct saved = preorderProductDao.savePreorderProduct(preorderProduct);
        PreorderProductStockDto preorderProductStock = new PreorderProductStockDto();
        preorderProductStock.setPreorderProductId(saved.getId());
        preorderProductStock.setStock(preorderProductDto.getStock());


        try {
            // 외부 서비스 호출
            ResponseEntity<Long> productStocks = stockServiceClient.savePreorderProductStocks(preorderProductStock);
            Long result = productStocks.getBody();
            log.info("PreorderProductServiceImpl - productStock = {} Timestamp = {}", result, LocalDateTime.now());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductStock.getPreorderProductId(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductStock.getPreorderProductId(), e.getMessage(), e);
            throw e;
        }


        return new PreorderProductResponseDto(saved);
    }


    @Override
    public OrderResponseDto submitOrder(Long userId, Long preorderProductId) {
        PreorderProduct preorderProduct = preorderProductDao.getPreorderProductById(preorderProductId);

        if (LocalDateTime.now().isAfter(preorderProduct.getAvailableFrom()) || (LocalDateTime.now().equals(preorderProduct.getAvailableFrom()))) {
            if (preorderProduct != null) {
                OrderDto orderDto = new OrderDto();
                orderDto.setUserId(userId);
                orderDto.setProductId(preorderProductId);
                orderDto.setProductType("preorderProduct");
                orderDto.setCount(1L);

                Long preorderProductStock = 0L;
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = stockServiceClient.getPreorderProductStock(preorderProductId);
                    preorderProductStock = productStocks.getBody();
                    log.info("PreorderProductServiceImpl - productStock = {} Timestamp = {}", preorderProductStock, LocalDateTime.now());
                } catch (HttpClientErrorException | HttpServerErrorException e) {
                    log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage());
                    throw e;
                } catch (Exception e) {
                    log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage(), e);
                    throw e;
                }
                // 현재 재고량이 0보다 크면 전체제고량 -1을 하고 주문
                if (preorderProductStock > 0) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = stockServiceClient.decrementPreorderProductStocks(preorderProductId);
                        preorderProductStock = productStocks.getBody();
                        log.info("PreorderProductServiceImpl - productStock = {} Timestamp = {}", preorderProductStock, LocalDateTime.now());
                    } catch (HttpClientErrorException | HttpServerErrorException e) {
                        log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage());
                        throw e;
                    } catch (Exception e) {
                        log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage(), e);
                        throw e;
                    }
                    if (preorderProductStock >= 0) {
                        try {
                            // 외부 서비스 호출
                            ResponseEntity<OrderResponseDto> responseDtoResponseEntity = orderServiceClient.saveOrder(orderDto);
                            OrderResponseDto result = responseDtoResponseEntity.getBody();
                            log.info("PreorderProductServiceImpl - order - preorderProductId = {} result = {} Timestamp = {}", result.getProductId(), result.getStatus(), LocalDateTime.now());
                            return result;
                        } catch (HttpClientErrorException | HttpServerErrorException e) {
                            log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage());
                            throw e;
                        } catch (Exception e) {
                            log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage(), e);
                            throw e;
                        }
                    }
                    throw new CustomException(ErrorCode.NOT_EXISTS_PREORDER_PRODUCT_STOCK);
                }
            }
        }
        return null;
    }

    @Override
    public PreorderProductResponseDto getPreorderProductById(Long preorderProductId) {
        return new PreorderProductResponseDto(preorderProductDao.getPreorderProductById(preorderProductId));
    }

    @Override
    public List<PreorderProductResponseDto> findAllPreorderProduct() {
        List<PreorderProduct> preorderProducts = preorderProductDao.findAllPreorderProduct();

        List<PreorderProductResponseDto> preorderProductResponseDtos = preorderProducts.stream()
                .map(PreorderProductResponseDto::new)
                .collect(Collectors.toList());

        return preorderProductResponseDtos;
    }


    @Override
    public void deletePreorderProduct(Long preorderProductId) {
        try {
            // 외부 서비스 호출
            ResponseEntity<Void> response = stockServiceClient.deletePreorderProductStocks(preorderProductId);
            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("ProductServiceImpl - productId = {} Timestamp = {}", preorderProductId, LocalDateTime.now());
            } else {
                log.error("ProductServiceImpl - fail delete stock 상태 코드: {}, 상품 ID: {}", response.getStatusCode(), preorderProductId);
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("HTTP 오류 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("재고 저장 중 예외 발생, 상품 ID: {}, 오류 메시지: {}", preorderProductId, e.getMessage(), e);
            throw e;
        }
        preorderProductDao.deletePreorderProduct(preorderProductId);
    }
}
