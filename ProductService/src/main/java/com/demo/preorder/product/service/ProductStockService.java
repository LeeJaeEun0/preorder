package com.demo.preorder.product.service;

import com.demo.preorder.product.entity.ProductStock;

public interface ProductStockService {
    ProductStock getProductById(Long productId);

    ProductStock incrementCount(Long productId, Long count);

    ProductStock decrementCount(Long productId, Long count);
}
