package com.demo.preorder.order.repository;

import com.demo.preorder.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByProductIdAndProductTypeAndStatus(Long id, String productType, String status);
}
