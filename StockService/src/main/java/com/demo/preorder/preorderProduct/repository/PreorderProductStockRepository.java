package com.demo.preorder.preorderProduct.repository;

import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreorderProductStockRepository extends JpaRepository<PreorderProductStock, Long> {
    Optional<PreorderProductStock> findByPreorderProductId(Long preorderProductId);
}
