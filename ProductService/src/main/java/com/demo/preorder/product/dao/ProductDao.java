package com.demo.preorder.product.dao;

import com.demo.preorder.product.entity.Product;

import java.util.List;

public interface ProductDao {
    Product saveProduct(Product product);

    Product getProductById(Long productId);

    List<Product> findAllProduct();

    void deleteProduct(Long productId);

}
