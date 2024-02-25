package com.demo.preorder.preorderProduct.service;

import com.demo.preorder.client.dto.OrderResponseDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductResponseDto;

import java.util.List;

public interface PreorderProductService {

    PreorderProductResponseDto savePreorderProduct(PreorderProductDto preorderProductDto);

    OrderResponseDto submitOrder(Long userId, Long preorderProductId);

    PreorderProductResponseDto getPreorderProductById(Long preorderProductId);

    List<PreorderProductResponseDto> findAllPreorderProduct();

    void deletePreorderProduct(Long preorderProductId);
}
