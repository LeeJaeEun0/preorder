package com.demo.preorder.product.dao;

import com.demo.preorder.product.entity.ProductStock;

public interface ProductStockDao {
    ProductStock saveProductStock(ProductStock productStock);

    ProductStock getProductById(Long productId);

    ProductStock incrementCount(Long productId, Long count);

    ProductStock decrementCount(Long productId, Long count);

    void updateProductStock(Long productId, Long stock);

    void deleteProductStock(Long productId);

}
