package com.demo.preorder.product.service;

import com.demo.preorder.product.client.dto.OrderResponseDto;
import com.demo.preorder.product.dto.ProductDto;
import com.demo.preorder.product.dto.ProductResponseDto;
import com.demo.preorder.product.dto.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(ProductDto productDto);

    OrderResponseDto submitOrder(Long userId, Long productId);

    ProductResponseDto getProductById(Long productId);

    List<ProductResponseDto> findAllProduct();

    ProductResponseDto changeProduct(Long productId,ProductUpdateDto productUpdateDto);

    void deleteProduct(Long productId);
}
