package com.demo.preorder.product.dao.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
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
        Optional<ProductStock> optionalProductStock = productStockRepository.findByProductId(productId);
        if (optionalProductStock.isPresent()) return optionalProductStock.get();
        else throw new CustomException(ErrorCode.INVALID_PRODUCT);
    }

    @Override
    public ProductStock incrementCount(Long productId) {
        Optional<ProductStock> optionalProductStock = productStockRepository.findByProductId(productId);
        if (optionalProductStock.isPresent()) {
            ProductStock productStock = optionalProductStock.get();
            productStock.setStock(productStock.getStock() + 1);
            return productStockRepository.save(productStock);
        }else throw new CustomException(ErrorCode.INVALID_PRODUCT);
    }

    @Override
    public ProductStock decrementCount(Long productId) {
        Optional<ProductStock> optionalProductStock = productStockRepository.findByProductId(productId);
        if (optionalProductStock.isPresent()) {
            ProductStock productStock = optionalProductStock.get();
            if (productStock.getStock() - 1 >= 0) {
                productStock.setStock(productStock.getStock() - 1);
                return productStockRepository.save(productStock);
            } else throw new CustomException(ErrorCode.NOT_EXISTS_PRODUCT_STOCK);
        }else throw new CustomException(ErrorCode.INVALID_PRODUCT);

    }

    @Override
    public void deleteProductStock(Long productId) {
        Optional<ProductStock> optionalProductStock = productStockRepository.findByProductId(productId);
        if (optionalProductStock.isPresent()) {
            ProductStock productStock = optionalProductStock.get();
            productStockRepository.delete(productStock);
        } else {
            throw new CustomException(ErrorCode.INVALID_PRODUCT);
        }
    }
}
