package com.demo.preorder.preorderProduct.dao.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.preorderProduct.dao.PreorderProductDao;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.preorderProduct.repository.PreorderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PreorderProductDaoImpl implements PreorderProductDao {
    private final PreorderProductRepository preorderProductRepository;

    @Override
    public PreorderProduct savePreorderProduct(PreorderProduct preorderProduct) {
        return preorderProductRepository.save(preorderProduct);
    }

    @Override
    public PreorderProduct getPreorderProductById(Long preorderProductId) {
        Optional<PreorderProduct> optionalPreorderProduct = preorderProductRepository.findById(preorderProductId);
        if (optionalPreorderProduct.isPresent()) {
            return optionalPreorderProduct.get();
        } else throw new CustomException(ErrorCode.INVALID_PREORDER_PRODUCT);
    }

    @Override
    public List<PreorderProduct> findAllPreorderProduct() {
        return preorderProductRepository.findAll();
    }

    @Override
    public void deletePreorderProduct(Long preorderProductId) {
        Optional<PreorderProduct> optionalPreorderProduct = preorderProductRepository.findById(preorderProductId);
        if (optionalPreorderProduct.isPresent()) {
            PreorderProduct preorderProduct = optionalPreorderProduct.get();
            preorderProductRepository.delete(preorderProduct);
        } else throw new CustomException(ErrorCode.INVALID_PREORDER_PRODUCT);
    }
}
