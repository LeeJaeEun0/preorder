package com.demo.preorder.preorderProduct.service.impl;

import com.demo.preorder.preorderProduct.dao.PreorderProductDao;
import com.demo.preorder.preorderProduct.dao.PreorderProductStockDao;
import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductUpdateDto;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.service.PreorderProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PreorderProductServiceImpl implements PreorderProductService {

    private final PreorderProductDao preorderProductDao;

    private final PreorderProductStockDao preorderProductStockDao;

    @Transactional
    @Override
    public PreorderProduct savePreorderProduct(PreorderProductDto preorderProductDto) {
        PreorderProduct preorderProduct = new PreorderProduct();
        preorderProduct.setTitle(preorderProductDto.getTitle());
        preorderProduct.setContent(preorderProductDto.getContent());
        preorderProduct.setPrice(preorderProductDto.getPrice());
        preorderProduct.setAvailableFrom(preorderProductDto.getAvailableFrom());
        PreorderProduct saved = preorderProductDao.savePreorderProduct(preorderProduct);
        if(saved != null) {
            PreorderProductStock preorderProductStock = new PreorderProductStock();
            preorderProductStock.setPreorderProductId(saved);
            preorderProductStock.setStock(preorderProductDto.getStock());
            preorderProductStockDao.savePreorderProductStock(preorderProductStock);
        }
        return saved;
    }

    @Override
    public PreorderProduct getPreorderProductById(Long preorderProductId) {
        return preorderProductDao.getPreorderProductById(preorderProductId);
    }

    @Override
    public List<PreorderProduct> findAllPreorderProduct() {
        return preorderProductDao.findAllPreorderProduct();
    }

    @Transactional
    @Override
    public PreorderProduct changePreorderProduct(Long preorderProductId,PreorderProductUpdateDto preorderProductUpdateDto) {
        preorderProductStockDao.updatePreorderProductStock(preorderProductId,preorderProductUpdateDto.getStock());
        return preorderProductDao.changePreorderProduct(preorderProductId, preorderProductUpdateDto.getTitle(), preorderProductUpdateDto.getContent(), preorderProductUpdateDto.getPrice(), preorderProductUpdateDto.getAvailableFrom());
    }

    @Transactional
    @Override
    public void deletePreorderProduct(Long preorderProductId) {
        preorderProductStockDao.deletePreorderProductStock(preorderProductId);
        preorderProductDao.deletePreorderProduct(preorderProductId);
    }
}
