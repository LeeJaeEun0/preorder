package com.demo.preorder.order.dao.impl;

import com.demo.preorder.order.dao.OrderDao;
import com.demo.preorder.order.entity.Order;
import com.demo.preorder.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            return  optionalOrder.get();
        }
        return null;
    }

    @Override
    public List<Order> findOrderSuccessById(Long orderId) {
        return orderRepository.findByIdAndStatus(orderId,"success");
    }

    @Override
    public List<Order> findOrderCancelById(Long orderId) {
        return orderRepository.findByIdAndStatus(orderId,"cancel");
    }

    @Override
    public Order updateOrder(Long orderId) {
        Optional<Order> optionalOrder =orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus("cancel");
            return orderRepository.save(order);
        }
        return null;
    }
}
