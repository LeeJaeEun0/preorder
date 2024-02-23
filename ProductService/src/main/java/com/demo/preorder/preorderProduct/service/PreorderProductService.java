package com.demo.preorder.preorderProduct.service;

import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductResponseDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductUpdateDto;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.product.client.dto.OrderResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PreorderProductService {

    PreorderProductResponseDto savePreorderProduct(PreorderProductDto preorderProductDto);

    OrderResponseDto submitOrder(Long userId, Long preorderProductId);

    PreorderProductResponseDto getPreorderProductById(Long preorderProductId);

    List<PreorderProductResponseDto> findAllPreorderProduct();

    PreorderProductResponseDto changePreorderProduct(Long preorderProductId,PreorderProductUpdateDto preorderProductUpdateDto);

    void deletePreorderProduct(Long preorderProductId);
}
