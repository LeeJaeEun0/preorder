package com.demo.preorder.preorderProduct.service.impl;

import com.demo.preorder.preorderProduct.dao.PreorderProductDao;
import com.demo.preorder.preorderProduct.dao.PreorderProductStockDao;
import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductUpdateDto;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.service.PreorderProductService;
import com.demo.preorder.product.client.dto.OrderDto;
import com.demo.preorder.product.client.dto.OrderResponseDto;
import com.demo.preorder.product.client.service.OrderServiceClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PreorderProductServiceImpl implements PreorderProductService {

    private final PreorderProductDao preorderProductDao;

    private final PreorderProductStockDao preorderProductStockDao;

    private final OrderServiceClient orderServiceClient;

    @Transactional
    @Override
    public PreorderProduct savePreorderProduct(PreorderProductDto preorderProductDto) {
        PreorderProduct preorderProduct = new PreorderProduct();
        preorderProduct.setTitle(preorderProductDto.getTitle());
        preorderProduct.setContent(preorderProductDto.getContent());
        preorderProduct.setPrice(preorderProductDto.getPrice());
        preorderProduct.setAvailableFrom(preorderProductDto.getAvailableFrom());
        PreorderProduct saved = preorderProductDao.savePreorderProduct(preorderProduct);
        if (saved != null) {
            PreorderProductStock preorderProductStock = new PreorderProductStock();
            preorderProductStock.setPreorderProductId(saved);
            preorderProductStock.setStock(preorderProductDto.getStock());
            preorderProductStockDao.savePreorderProductStock(preorderProductStock);
        }
        return saved;
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
                orderDto.setTotalAmount(preorderProduct.getPrice());

                PreorderProductStock productStock = preorderProductStockDao.decrementCount(preorderProductId);
                if (productStock.getStock() >= 0) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<OrderResponseDto> responseDtoResponseEntity = orderServiceClient.saveOrder(orderDto);
                        OrderResponseDto result = responseDtoResponseEntity.getBody();
                        log.info("Info log: order - preorderProductId ={} result={}", result.getProductId(), result.getStatus());
                        return result;
                    } catch (Exception e) {
                        // 오류 발생 시 처리
                        log.error("Error order : {}", e.getMessage(), e);
                        return null;
                        // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                    }
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public PreorderProduct getPreorderProductById(Long preorderProductId) {
        return preorderProductDao.getPreorderProductById(preorderProductId);
    }

    @Override
    public List<PreorderProduct> findAllPreorderProduct() {
        return preorderProductDao.findAllPreorderProduct();
    }

    @Transactional
    @Override
    public PreorderProduct changePreorderProduct(Long preorderProductId, PreorderProductUpdateDto preorderProductUpdateDto) {
        preorderProductStockDao.updatePreorderProductStock(preorderProductId, preorderProductUpdateDto.getStock());
        return preorderProductDao.changePreorderProduct(preorderProductId, preorderProductUpdateDto.getTitle(), preorderProductUpdateDto.getContent(), preorderProductUpdateDto.getPrice(), preorderProductUpdateDto.getAvailableFrom());
    }

    @Transactional
    @Override
    public void deletePreorderProduct(Long preorderProductId) {
        preorderProductStockDao.deletePreorderProductStock(preorderProductId);
        preorderProductDao.deletePreorderProduct(preorderProductId);
    }
}
