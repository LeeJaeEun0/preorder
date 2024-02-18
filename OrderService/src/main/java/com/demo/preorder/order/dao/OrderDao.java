package com.demo.preorder.order.dao;

import com.demo.preorder.order.entity.Order;

import java.util.List;

public interface OrderDao {

    Order saveOrder(Order order);

    Order getOrderById(Long orderId);

    List<Order> findOrderSuccessById(Long productId, String productType);

    List<Order> findOrderCancelById(Long productId, String productType);

    Order updateOrder(Long orderId);
}
