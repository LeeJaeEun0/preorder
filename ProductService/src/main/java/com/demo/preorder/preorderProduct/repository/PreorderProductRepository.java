package com.demo.preorder.preorderProduct.repository;

import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreorderProductRepository extends JpaRepository<PreorderProduct, Long> {
}
