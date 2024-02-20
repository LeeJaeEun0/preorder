package com.demo.preorder.preorderProduct.service.impl;

import com.demo.preorder.preorderProduct.dao.PreorderProductStockDao;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.service.PreorderProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreorderProductStockServiceImpl implements PreorderProductStockService {

    private final PreorderProductStockDao preorderProductStockDao;

    @Override
    @Cacheable(value = "productCache", key = "#preorderProductId")
    public PreorderProductStock getPreorderProductById(Long preorderProductId) {
        return preorderProductStockDao.getPreorderProductById(preorderProductId);
    }

    @Override
    @CachePut(value = "productCache", key = "#preorderProductId")
    public PreorderProductStock incrementCount(Long preorderProductId) {
        return preorderProductStockDao.incrementCount(preorderProductId);
    }

    @Override
    public PreorderProductStock decrementCount(Long preorderProductId) {
        return preorderProductStockDao.decrementCount(preorderProductId);
    }
}
