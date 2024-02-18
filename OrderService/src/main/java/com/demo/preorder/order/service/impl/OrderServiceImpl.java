package com.demo.preorder.order.service.impl;

import com.demo.preorder.order.dao.OrderDao;
import com.demo.preorder.order.dto.OrderDto;
import com.demo.preorder.order.dto.OrderResponseDto;
import com.demo.preorder.order.entity.Order;
import com.demo.preorder.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Override
    public OrderResponseDto saveOrder(OrderDto orderDto) {
        Random random = new Random();

        // 80%의 사람만 주문한다고 가정
        // 20%의 사람은 변심으로 주문을 취소한다고 가정
        if (random.nextDouble() < 0.8)  {
            Order order = new Order();
            order.setUserId(orderDto.getUserId());
            order.setProductId(orderDto.getProductId());
            order.setProductType(orderDto.getProductType());
            order.setCount(orderDto.getCount());
            order.setTotalAmount(orderDto.getTotalAmount());
            order.setStatus("success");

            Order saveOrder = orderDao.saveOrder(order);

            if (saveOrder != null) {
                OrderResponseDto orderResponseDto = new OrderResponseDto();
                orderResponseDto.setProductId(saveOrder.getProductId());
                orderResponseDto.setCount(saveOrder.getCount());
                orderResponseDto.setTotalAmount(saveOrder.getTotalAmount());
                orderResponseDto.setStatus(saveOrder.getStatus());
                return orderResponseDto;
            }
        } else {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setStatus("failure");
            return orderResponseDto;
        }
        return null;
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderDao.getOrderById(orderId);
        if(order != null){
            OrderResponseDto orderResponseDto =new OrderResponseDto();
            orderResponseDto.setProductId(order.getProductId());
            orderResponseDto.setCount(order.getCount());
            orderResponseDto.setTotalAmount(order.getTotalAmount());
            orderResponseDto.setStatus(order.getStatus());
            return orderResponseDto;
        }
        return null;
    }

    @Override
    public List<OrderResponseDto> findOrderSuccessById(Long productId, String productType) {
        List<Order> orderList = orderDao.findOrderSuccessById(productId,productType);
        if(orderList != null) {
            return orderList.stream()
                    .map(order -> {
                        OrderResponseDto orderResponseDto = new OrderResponseDto();
                        orderResponseDto.setProductId(order.getProductId());
                        orderResponseDto.setCount(order.getCount());
                        orderResponseDto.setTotalAmount(order.getTotalAmount());
                        orderResponseDto.setStatus(order.getStatus());
                        return orderResponseDto; // 설정된 DTO 반환
                    })
                    .collect(Collectors.toList());
        }
        return  null;
    }

    @Override
    public List<OrderResponseDto> findOrderCancelById(Long productId, String productType) {

        List<Order> orderList = orderDao.findOrderCancelById(productId,productType);
        if(orderList != null) {
            return orderList.stream()
                    .map(order -> {
                        OrderResponseDto orderResponseDto = new OrderResponseDto();
                        orderResponseDto.setProductId(order.getProductId());
                        orderResponseDto.setCount(order.getCount());
                        orderResponseDto.setTotalAmount(order.getTotalAmount());
                        orderResponseDto.setStatus(order.getStatus());
                        return orderResponseDto; // 설정된 DTO 반환
                    })
                    .collect(Collectors.toList());
        }
        return  null;
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId) {
        Order order = orderDao.updateOrder(orderId);
        if(order != null){
            OrderResponseDto orderResponseDto =new OrderResponseDto();
            orderResponseDto.setProductId(order.getProductId());
            orderResponseDto.setCount(order.getCount());
            orderResponseDto.setTotalAmount(order.getTotalAmount());
            orderResponseDto.setStatus(order.getStatus());
            return orderResponseDto;
        }
        return null;
    }
}
