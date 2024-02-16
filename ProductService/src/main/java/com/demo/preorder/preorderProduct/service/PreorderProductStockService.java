package com.demo.preorder.preorderProduct.service;

import com.demo.preorder.preorderProduct.entity.PreorderProductStock;

public interface PreorderProductStockService {
    PreorderProductStock getPreorderProductById(Long preorderProductId);

    PreorderProductStock incrementCount(Long preorderProductId, Long count);

    PreorderProductStock decrementCount(Long preorderProductId, Long count);

}
