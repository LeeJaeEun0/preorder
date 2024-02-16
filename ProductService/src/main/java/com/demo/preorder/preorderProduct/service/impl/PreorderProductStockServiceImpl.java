package com.demo.preorder.preorderProduct.service.impl;

import com.demo.preorder.preorderProduct.dao.PreorderProductStockDao;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.service.PreorderProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreorderProductStockServiceImpl implements PreorderProductStockService {

    private final PreorderProductStockDao preorderProductStockDao;

    @Override
    public PreorderProductStock getPreorderProductById(Long preorderProductId) {
        return preorderProductStockDao.getPreorderProductById(preorderProductId);
    }

    @Override
    public PreorderProductStock incrementCount(Long preorderProductId, Long count) {
        return preorderProductStockDao.incrementCount(preorderProductId,count);
    }

    @Override
    public PreorderProductStock decrementCount(Long preorderProductId, Long count) {
        return preorderProductStockDao.decrementCount(preorderProductId,count);
    }
}
