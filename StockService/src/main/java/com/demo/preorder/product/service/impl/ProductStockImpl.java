package com.demo.preorder.product.service.impl;

import com.demo.preorder.product.dao.ProductStockDao;
import com.demo.preorder.product.dto.ProductStockDto;
import com.demo.preorder.product.dto.ProductStockResponseDto;
import com.demo.preorder.product.entity.ProductStock;
import com.demo.preorder.product.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStockImpl implements ProductStockService {

    private final ProductStockDao productStockDao;

    @Override
    public ProductStockResponseDto saveProduct(ProductStockDto productStockDto) {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(productStockDto.getProductId());
        productStock.setStock(productStockDto.getStock());
        return new ProductStockResponseDto(productStockDao.saveProductStock(productStock));
    }

    @Override
    public ProductStockResponseDto getProductById(Long productId) {
        return new ProductStockResponseDto(productStockDao.getProductById(productId));
    }

    @Override
    public ProductStockResponseDto incrementCount(Long productId) {
        return new ProductStockResponseDto(productStockDao.incrementCount(productId));
    }

    @Override
    public ProductStockResponseDto decrementCount(Long productId) {
        return new ProductStockResponseDto(productStockDao.decrementCount(productId));
    }

    @Override
    public void deleteProduct(Long productId) {
        productStockDao.deleteProductStock(productId);
    }
}
