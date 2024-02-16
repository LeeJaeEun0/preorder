package com.demo.preorder.product.service.impl;

import com.demo.preorder.product.dao.ProductDao;
import com.demo.preorder.product.dao.ProductStockDao;
import com.demo.preorder.product.dto.ProductDto;
import com.demo.preorder.product.dto.ProductUpdateDto;
import com.demo.preorder.product.entity.Product;
import com.demo.preorder.product.entity.ProductStock;
import com.demo.preorder.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final ProductStockDao productStockDao;
    @Override
    public Product saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setContent(productDto.getContent());
        product.setPrice(productDto.getPrice());
        Product savedProduct = productDao.saveProduct(product);

        if (savedProduct != null) {
            ProductStock productStock = new ProductStock();
            productStock.setProductId(savedProduct);
            productStock.setStock(productDto.getStock());
            productStockDao.saveProductStock(productStock);
        }
        return savedProduct;
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public List<Product> findAllProduct() {
        return productDao.findAllProduct();
    }

    @Override
    public Product changeProduct(Long productId,ProductUpdateDto productUpdateDto) {
        productStockDao.updateProductStock(productId,productUpdateDto.getStock());
        return productDao.changeProduct(productId, productUpdateDto.getTitle(), productUpdateDto.getContent(), productUpdateDto.getPrice());
    }

    @Override
    public void deleteProduct(Long productId) {
        productStockDao.deleteProductStock(productId);
        productDao.deleteProduct(productId);
    }
}
