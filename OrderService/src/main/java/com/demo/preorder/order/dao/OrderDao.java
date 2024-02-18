package com.demo.preorder.order.dao;

import com.demo.preorder.order.entity.Order;

import java.util.List;

public interface OrderDao {

    Order saveOrder(Order order);

    Order getOrderById(Long orderId);

    List<Order> findOrderSuccessById(Long orderId);

    List<Order> findOrderCancelById(Long orderId);

    Order updateOrder(Long orderId);
}
