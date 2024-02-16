package com.demo.preorder.product.service;

import com.demo.preorder.product.dto.ProductDto;
import com.demo.preorder.product.dto.ProductUpdateDto;
import com.demo.preorder.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(ProductDto productDto);

    Product getProductById(Long productId);

    List<Product> findAllProduct();

    Product changeProduct(Long productId,ProductUpdateDto productUpdateDto);

    void deleteProduct(Long productId);
}
