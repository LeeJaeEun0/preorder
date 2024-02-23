package com.demo.preorder.order.dao.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
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
        }else throw new CustomException(ErrorCode.INVALID_ORDER);
    }

    @Override
    public List<Order> findOrderSuccessById(Long productId, String productType) {
        return orderRepository.findByProductIdAndProductTypeAndStatus(productId,productType,"success");
    }

    @Override
    public List<Order> findOrderCancelById(Long productId, String productType) {
        return orderRepository.findByProductIdAndProductTypeAndStatus(productId,productType,"cancel");
    }

    @Override
    public Order updateOrder(Long orderId) {
        Optional<Order> optionalOrder =orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus("cancel");
            return orderRepository.save(order);
        }else throw new CustomException(ErrorCode.INVALID_ORDER);
    }
}
