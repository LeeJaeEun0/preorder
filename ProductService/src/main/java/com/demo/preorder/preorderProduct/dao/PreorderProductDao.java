package com.demo.preorder.preorderProduct.dao;

import com.demo.preorder.preorderProduct.entity.PreorderProduct;

import java.util.List;

public interface PreorderProductDao {

    PreorderProduct savePreorderProduct(PreorderProduct preorderProduct);

    PreorderProduct getPreorderProductById(Long preorderProductId);

    List<PreorderProduct> findAllPreorderProduct();

    void deletePreorderProduct(Long preorderProductId);

}
