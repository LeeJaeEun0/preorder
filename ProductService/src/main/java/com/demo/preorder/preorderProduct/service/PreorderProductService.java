package com.demo.preorder.preorderProduct.service;

import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductUpdateDto;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.product.client.dto.OrderResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PreorderProductService {

    PreorderProduct savePreorderProduct(PreorderProductDto preorderProductDto);

    OrderResponseDto submitOrder(Long userId, Long preorderProductId);

    PreorderProduct getPreorderProductById(Long preorderProductId);

    List<PreorderProduct> findAllPreorderProduct();

    PreorderProduct changePreorderProduct(Long preorderProductId,PreorderProductUpdateDto preorderProductUpdateDto);

    void deletePreorderProduct(Long preorderProductId);
}
