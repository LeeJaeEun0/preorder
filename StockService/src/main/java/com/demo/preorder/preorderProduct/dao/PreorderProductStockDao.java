package com.demo.preorder.preorderProduct.dao;

import com.demo.preorder.preorderProduct.entity.PreorderProductStock;

public interface PreorderProductStockDao {
    PreorderProductStock savePreorderProductStock(PreorderProductStock PreorderProductStock);

    PreorderProductStock getPreorderProductById(Long preorderProductId);

    PreorderProductStock incrementCount(Long preorderProductId);

    PreorderProductStock decrementCount(Long preorderProductId);

    void deletePreorderProductStock(Long preorderProductId);
}
