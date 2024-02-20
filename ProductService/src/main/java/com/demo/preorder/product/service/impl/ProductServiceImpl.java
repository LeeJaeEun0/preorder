package com.demo.preorder.product.service.impl;

import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.product.client.dto.OrderDto;
import com.demo.preorder.product.client.dto.OrderResponseDto;
import com.demo.preorder.product.client.service.OrderServiceClient;
import com.demo.preorder.product.dao.ProductDao;
import com.demo.preorder.product.dao.ProductStockDao;
import com.demo.preorder.product.dto.ProductDto;
import com.demo.preorder.product.dto.ProductUpdateDto;
import com.demo.preorder.product.entity.Product;
import com.demo.preorder.product.entity.ProductStock;
import com.demo.preorder.product.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final ProductStockDao productStockDao;

    private final OrderServiceClient orderServiceClient;

    @Transactional
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
    public OrderResponseDto submitOrder(Long userId, Long productId) {
        Product product = productDao.getProductById(productId);
        if (product != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setUserId(userId);
            orderDto.setProductId(productId);
            orderDto.setProductType("product");
            orderDto.setCount(1L);
            orderDto.setTotalAmount(product.getPrice());

            ProductStock savedProductStock = productStockDao.getProductById(productId);
            if (savedProductStock != null && savedProductStock.getStock() > 0) {
                ProductStock productStock = productStockDao.decrementCount(productId);
                if (productStock.getStock() >= 0) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<OrderResponseDto> responseDtoResponseEntity = orderServiceClient.saveOrder(orderDto);
                        OrderResponseDto result = responseDtoResponseEntity.getBody();
                        log.info("Info log: order - productId ={} result={}", result.getProductId(), result.getStatus());
                        return result;
                    } catch (Exception e) {
                        // 오류 발생 시 처리
                        log.error("Error order : {}", e.getMessage(), e);
                        return null;
                        // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                    }
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public List<Product> findAllProduct() {
        return productDao.findAllProduct();
    }

    @Transactional
    @Override
    public Product changeProduct(Long productId, ProductUpdateDto productUpdateDto) {
        productStockDao.updateProductStock(productId, productUpdateDto.getStock());
        return productDao.changeProduct(productId, productUpdateDto.getTitle(), productUpdateDto.getContent(), productUpdateDto.getPrice());
    }

    @Transactional
    @Override
    public void deleteProduct(Long productId) {
        productStockDao.deleteProductStock(productId);
        productDao.deleteProduct(productId);
    }
}
