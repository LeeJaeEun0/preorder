package com.demo.preorder.preorderProduct.dao.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.preorderProduct.dao.PreorderProductStockDao;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.repository.PreorderProductStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PreorderProductStockDaoImpl implements PreorderProductStockDao {

    private final PreorderProductStockRepository preorderProductStockRepository;

    @Override
    public PreorderProductStock savePreorderProductStock(PreorderProductStock preorderProductStock) {
        return preorderProductStockRepository.save(preorderProductStock);
    }

    @Override
    public PreorderProductStock getPreorderProductById(Long preorderProductId) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductId(preorderProductId);
        if (optionalPreorderProductStock.isPresent()) {
            return optionalPreorderProductStock.get();
        } else throw new CustomException(ErrorCode.INVALID_PREORDER_PRODUCT_STOCK);
    }

    @Override
    public PreorderProductStock incrementCount(Long preorderProductId) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductId(preorderProductId);
        if (optionalPreorderProductStock.isPresent()) {
            PreorderProductStock preorderProductStock = optionalPreorderProductStock.get();
            preorderProductStock.setStock(preorderProductStock.getStock() + 1);
            return preorderProductStockRepository.save(preorderProductStock);
        } else throw new CustomException(ErrorCode.INVALID_PREORDER_PRODUCT_STOCK);
    }

    @Override
    public PreorderProductStock decrementCount(Long preorderProductId) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductId(preorderProductId);
        if (optionalPreorderProductStock.isPresent()) {
            PreorderProductStock preorderProductStock = optionalPreorderProductStock.get();
            if (preorderProductStock.getStock() - 1 >= 0) {
                preorderProductStock.setStock(preorderProductStock.getStock() - 1);
                return preorderProductStockRepository.save(preorderProductStock);
            } else throw new CustomException(ErrorCode.NOT_EXISTS_PREORDER_PRODUCT_STOCK);
        } else throw new CustomException(ErrorCode.INVALID_PREORDER_PRODUCT_STOCK);
    }

    @Override
    public void deletePreorderProductStock(Long preorderProductId) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductId(preorderProductId);
        if (optionalPreorderProductStock.isPresent()) {
            PreorderProductStock preorderProductStock = optionalPreorderProductStock.get();
            preorderProductStockRepository.delete(preorderProductStock);
        } else {
            throw new CustomException(ErrorCode.INVALID_PREORDER_PRODUCT_STOCK);
        }
    }
}
