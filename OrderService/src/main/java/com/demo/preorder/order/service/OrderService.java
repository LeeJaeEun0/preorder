package com.demo.preorder.order.service;

import com.demo.preorder.order.dto.OrderDto;
import com.demo.preorder.order.dto.OrderResponseDto;
import com.demo.preorder.order.entity.Order;

import java.util.List;

public interface OrderService {


    OrderResponseDto saveOrder(OrderDto orderDto);

    OrderResponseDto getOrderById(Long orderId);

    List<OrderResponseDto> findOrderSuccessById(Long productId, String productType);

    List<OrderResponseDto> findOrderCancelById(Long productId, String productType);

    OrderResponseDto updateOrder(Long orderId);
}
