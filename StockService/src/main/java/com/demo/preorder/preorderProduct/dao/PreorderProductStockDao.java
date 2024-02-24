package com.demo.preorder.preorderProduct.dao;

import com.demo.preorder.preorderProduct.entity.PreorderProductStock;

public interface PreorderProductStockDao {
    PreorderProductStock savePreorderProductStock(PreorderProductStock PreorderProductStock);

    PreorderProductStock getPreorderProductById(Long preorderProductId);

    PreorderProductStock incrementCount(Long preorderProductId);

    PreorderProductStock decrementCount(Long preorderProductId);

    void updatePreorderProductStock(Long preorderProductId, Long stock);

    void deletePreorderProductStock(Long preorderProductId);
}
