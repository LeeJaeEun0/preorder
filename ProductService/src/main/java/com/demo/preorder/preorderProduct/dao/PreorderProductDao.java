package com.demo.preorder.preorderProduct.dao;

import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.product.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface PreorderProductDao {

    PreorderProduct savePreorderProduct(PreorderProduct preorderProduct);

    PreorderProduct getPreorderProductById(Long preorderProductId);

    List<PreorderProduct> findAllPreorderProduct();

    void deletePreorderProduct(Long preorderProductId);

}
