package com.demo.preorder.preorderProduct.service;

import com.demo.preorder.preorderProduct.dto.PreorderProductStockDto;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;

public interface PreorderProductStockService {
    PreorderProductStock savePreorderProduct(PreorderProductStockDto preorderProductStockDto);

    PreorderProductStock getPreorderProductById(Long preorderProductId);

    PreorderProductStock incrementCount(Long preorderProductId);

    PreorderProductStock decrementCount(Long preorderProductId);

    void deletePreorderProduct(Long preorderProductId);
}
