package com.demo.preorder.product.dao.impl;

import com.demo.preorder.product.dao.ProductStockDao;
import com.demo.preorder.product.entity.ProductStock;
import com.demo.preorder.product.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStockDaoImpl implements ProductStockDao {

    private final ProductStockRepository productStockRepository;

    @Override
    public ProductStock saveProductStock(ProductStock productStock) {
        return productStockRepository.save(productStock);
    }

    @Override
    public ProductStock getProductById(Long productId) {
        Optional<ProductStock>  optionalProductStock = productStockRepository.findByProductIdId(productId);
        if(optionalProductStock.isPresent()) return optionalProductStock.get();
        return null;
    }

    @Override
    public ProductStock incrementCount(Long productId, Long count) {
        Optional<ProductStock>  optionalProductStock = productStockRepository.findByProductIdId(productId);
        if(optionalProductStock.isPresent()){
            ProductStock productStock = optionalProductStock.get();
            productStock.setStock(productStock.getStock()+count);
            return productStockRepository.save(productStock);
        }

        return null;
    }

    @Override
    public ProductStock decrementCount(Long productId, Long count) {
        Optional<ProductStock>  optionalProductStock = productStockRepository.findByProductIdId(productId);
        if(optionalProductStock.isPresent()) {
            ProductStock productStock = optionalProductStock.get();
            if(productStock.getStock()-count >= 0){
            productStock.setStock(productStock.getStock() - count);
            return productStockRepository.save(productStock);
            }
            else return null;
        }
        return  null;
    }

    @Override
    public void updateProductStock(Long productId, Long stock) {
        Optional<ProductStock>  optionalProductStock = productStockRepository.findByProductIdId(productId);
        if(optionalProductStock.isPresent()) {
            ProductStock productStock = optionalProductStock.get();
            productStock.setStock(stock);
            productStockRepository.save(productStock);
            log.info("info log = stock 변경 성공");
        }else {
            log.info("info log = stock 변경 실패");
        }
    }

    @Override
    public void deleteProductStock(Long productId) {
        Optional<ProductStock> optionalProductStock = productStockRepository.findByProductIdId(productId);
        log.info("info log: {}",optionalProductStock);
        if(optionalProductStock.isPresent()) {
            ProductStock productStock = optionalProductStock.get();
            productStockRepository.delete(productStock);
            log.info("info log = stock 삭제 성공");
        }else{
            log.error("info log = stock 삭제 실패");
        }
    }
}
