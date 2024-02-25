package com.demo.preorder.product.dao;

import com.demo.preorder.product.entity.ProductStock;

public interface ProductStockDao {
    ProductStock saveProductStock(ProductStock productStock);

    ProductStock getProductById(Long productId);

    ProductStock incrementCount(Long productId);

    ProductStock decrementCount(Long productId);

    void deleteProductStock(Long productId);

}
