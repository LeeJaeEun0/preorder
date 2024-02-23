package com.demo.preorder.product.service;

import com.demo.preorder.product.dto.ProductStockResponseDto;

public interface ProductStockService {
    ProductStockResponseDto getProductById(Long productId);

    ProductStockResponseDto incrementCount(Long productId);

    ProductStockResponseDto decrementCount(Long productId);
}
