package com.demo.preorder.order.service;

import com.demo.preorder.order.dto.OrderDto;
import com.demo.preorder.order.dto.OrderResponseDto;
import com.demo.preorder.order.entity.Order;

import java.util.List;

public interface OrderService {


    OrderResponseDto saveOrder(OrderDto orderDto);

    OrderResponseDto getOrderById(Long orderId);

    List<OrderResponseDto> findOrderSuccessById(Long orderId);

    List<OrderResponseDto> findOrderCancelById(Long orderId);

    OrderResponseDto updateOrder(Long orderId);
}
