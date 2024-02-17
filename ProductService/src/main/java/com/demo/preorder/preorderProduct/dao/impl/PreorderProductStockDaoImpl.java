package com.demo.preorder.preorderProduct.dao.impl;

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
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductIdId(preorderProductId);
        if(optionalPreorderProductStock.isPresent()){
            return optionalPreorderProductStock.get();
        }
        else return null;
    }

    @Override
    public PreorderProductStock incrementCount(Long preorderProductId) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductIdId(preorderProductId);
        if(optionalPreorderProductStock.isPresent()){
            PreorderProductStock preorderProductStock = optionalPreorderProductStock.get();
            preorderProductStock.setStock(preorderProductStock.getStock()+1);
            return preorderProductStockRepository.save(preorderProductStock);
        }
        return null;
    }

    @Override
    public PreorderProductStock decrementCount(Long preorderProductId) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductIdId(preorderProductId);
        if(optionalPreorderProductStock.isPresent()){
            PreorderProductStock preorderProductStock = optionalPreorderProductStock.get();
            if (preorderProductStock.getStock() - 1 >=0){
                preorderProductStock.setStock(preorderProductStock.getStock()-1);
                return preorderProductStockRepository.save(preorderProductStock);
            }
            return null;
        }
        return null;
    }

    @Override
    public void updatePreorderProductStock(Long preorderProductId, Long stock) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductIdId(preorderProductId);
        if(optionalPreorderProductStock.isPresent()){
            PreorderProductStock preorderProductStock = optionalPreorderProductStock.get();
            preorderProductStock.setStock(stock);
            preorderProductStockRepository.save(preorderProductStock);
            log.info("info log = stock 변경 성공");
        }else {
            log.error("info log = stock 변경 실패");
        }
    }

    @Override
    public void deletePreorderProductStock(Long preorderProductId) {
        Optional<PreorderProductStock> optionalPreorderProductStock = preorderProductStockRepository.findByPreorderProductIdId(preorderProductId);
        if(optionalPreorderProductStock.isPresent()){
            PreorderProductStock preorderProductStock = optionalPreorderProductStock.get();
            preorderProductStockRepository.delete(preorderProductStock);
            log.info("info log = stock 삭제 성공");
        }else {
            log.error("info log = stock 삭제 실패");
        }
    }
}
