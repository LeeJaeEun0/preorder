package com.demo.preorder.product.service.impl;

import com.demo.preorder.product.dao.ProductStockDao;
import com.demo.preorder.product.entity.ProductStock;
import com.demo.preorder.product.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStockImpl implements ProductStockService {

    private final ProductStockDao productStockDao;

    @Override
    public ProductStock getProductById(Long productId) {
        return productStockDao.getProductById(productId);
    }

    @Override
    public ProductStock incrementCount(Long productId) {
        return productStockDao.incrementCount(productId);
    }

    @Override
    public ProductStock decrementCount(Long productId) {
        return productStockDao.decrementCount(productId);
    }
}
