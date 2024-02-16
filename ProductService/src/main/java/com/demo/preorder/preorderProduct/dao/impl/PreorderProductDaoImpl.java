package com.demo.preorder.preorderProduct.dao.impl;

import com.demo.preorder.preorderProduct.dao.PreorderProductDao;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.preorderProduct.repository.PreorederProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PreorderProductDaoImpl implements PreorderProductDao {
    private final PreorederProductRepository preorederProductRepository;

    @Override
    public PreorderProduct savePreorderProduct(PreorderProduct preorderProduct) {
        return preorederProductRepository.save(preorderProduct);
    }

    @Override
    public PreorderProduct getPreorderProductById(Long preorderProductId) {
        Optional<PreorderProduct> optionalPreorderProduct = preorederProductRepository.findById(preorderProductId);
        if (optionalPreorderProduct.isPresent()){
            return optionalPreorderProduct.get();
        }
        return null;
    }

    @Override
    public List<PreorderProduct> findAllPreorderProduct() {
        return preorederProductRepository.findAll();
    }

    @Override
    public PreorderProduct changePreorderProduct(Long preorderProductId, String title, String content, Long price, LocalDateTime availableFrom) {
        Optional<PreorderProduct> optionalPreorderProduct = preorederProductRepository.findById(preorderProductId);
        if (optionalPreorderProduct.isPresent()){
            PreorderProduct preorderProduct = optionalPreorderProduct.get();
            preorderProduct.setTitle(title);
            preorderProduct.setContent(content);
            preorderProduct.setAvailableFrom(availableFrom);
            return preorederProductRepository.save(preorderProduct);
        }
        return null;
    }

    @Override
    public void deletePreorderProduct(Long preorderProductId) {
        Optional<PreorderProduct> optionalPreorderProduct = preorederProductRepository.findById(preorderProductId);
        if (optionalPreorderProduct.isPresent()){
            PreorderProduct preorderProduct = optionalPreorderProduct.get();
            preorederProductRepository.delete(preorderProduct);
        }
    }
}
