package com.demo.preorder.product.repository;

import com.demo.preorder.product.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

    Optional<ProductStock> findByProductIdId(Long productId);
}
