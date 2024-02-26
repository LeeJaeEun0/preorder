package com.demo.preorder.product.service;

import com.demo.preorder.product.dto.ProductStockDto;
import com.demo.preorder.product.dto.ProductStockResponseDto;

public interface ProductStockService {
    ProductStockResponseDto saveProduct(ProductStockDto productStockDto);

    ProductStockResponseDto getProductById(Long productId);

    ProductStockResponseDto incrementCount(Long productId);

    ProductStockResponseDto decrementCount(Long productId);

    void deleteProduct(Long productId);

}
