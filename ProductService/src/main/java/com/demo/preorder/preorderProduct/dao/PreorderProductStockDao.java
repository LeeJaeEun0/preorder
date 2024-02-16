package com.demo.preorder.preorderProduct.dao;

import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.product.entity.ProductStock;

public interface PreorderProductStockDao {
    PreorderProductStock savePreorderProductStock(PreorderProductStock PreorderProductStock);

    PreorderProductStock getPreorderProductById(Long preorderProductId);

    PreorderProductStock incrementCount(Long preorderProductId, Long count);

    PreorderProductStock decrementCount(Long preorderProductId, Long count);

    void updatePreorderProductStock(Long preorderProductId, Long stock);

    void deletePreorderProductStock(Long preorderProductId);
}
