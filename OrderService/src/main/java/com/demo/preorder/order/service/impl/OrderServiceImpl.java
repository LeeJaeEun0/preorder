package com.demo.preorder.order.service.impl;

import com.demo.preorder.order.client.dto.PaymentDto;
import com.demo.preorder.order.client.dto.PaymentResponseDto;
import com.demo.preorder.order.client.service.PaymentServiceClient;
import com.demo.preorder.order.client.service.ProductServiceClient;
import com.demo.preorder.order.dao.OrderDao;
import com.demo.preorder.order.dto.OrderDto;
import com.demo.preorder.order.dto.OrderResponseDto;
import com.demo.preorder.order.entity.Order;
import com.demo.preorder.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    private final ProductServiceClient productServiceClient;

    private final PaymentServiceClient paymentServiceClient;

    @Override
    public OrderResponseDto saveOrder(OrderDto orderDto) {
        Random random = new Random();

        // 80%의 사람만 주문한다고 가정
        // 20%의 사람은 변심으로 주문을 취소한다고 가정
        if (random.nextDouble() < 0.8) {
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

                try {
                    PaymentDto paymentDto = new PaymentDto();
                    paymentDto.setUserId(saveOrder.getUserId());
                    paymentDto.setOrderId(saveOrder.getId());
                    paymentDto.setProductId(saveOrder.getProductId());
                    paymentDto.setProductType(saveOrder.getProductType());

                    // 외부 서비스 호출
                    ResponseEntity<PaymentResponseDto> productStocks = paymentServiceClient.savePayment(paymentDto);
                    PaymentResponseDto result = productStocks.getBody();
                    log.info("Info log: payment - {} ", result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving payment {}", e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }


                return orderResponseDto;
            }
        } else {

            Order order = new Order();
            order.setUserId(orderDto.getUserId());
            order.setProductId(orderDto.getProductId());
            order.setProductType(orderDto.getProductType());
            order.setCount(orderDto.getCount());
            order.setTotalAmount(orderDto.getTotalAmount());
            order.setStatus("cancel");

            Order saveOrder = orderDao.saveOrder(order);

            if (saveOrder != null) {
                OrderResponseDto orderResponseDto = new OrderResponseDto();
                orderResponseDto.setProductId(saveOrder.getProductId());
                orderResponseDto.setCount(saveOrder.getCount());
                orderResponseDto.setTotalAmount(saveOrder.getTotalAmount());
                orderResponseDto.setStatus(saveOrder.getStatus());


                if (saveOrder.getProductType().equals("product")) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = productServiceClient.incrementProductStocks(orderDto.getProductId());
                        Long result = productStocks.getBody();
                        log.info("Info log: productStock - {} ", result);
                    } catch (Exception e) {
                        // 오류 발생 시 처리
                        log.error("Error saving productStock {}", e.getMessage(), e);
                        // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                    }

                } else if (saveOrder.getProductType().equals("preorderProduct")) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = productServiceClient.incrementPreorderProductStocks(orderDto.getProductId());
                        Long result = productStocks.getBody();
                        log.info("Info log: productStock - {} ", result);
                    } catch (Exception e) {
                        // 오류 발생 시 처리
                        log.error("Error saving productStock {}", e.getMessage(), e);
                        // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                    }

                }

                return orderResponseDto;
            }
        }
        return null;
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderDao.getOrderById(orderId);
        if (order != null) {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
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
        List<Order> orderList = orderDao.findOrderSuccessById(productId, productType);
        if (orderList != null) {
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
        return null;
    }

    @Override
    public List<OrderResponseDto> findOrderCancelById(Long productId, String productType) {

        List<Order> orderList = orderDao.findOrderCancelById(productId, productType);
        if (orderList != null) {
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
        return null;
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId) {
        Order saveOrder = orderDao.updateOrder(orderId);
        if (saveOrder != null) {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setProductId(saveOrder.getProductId());
            orderResponseDto.setCount(saveOrder.getCount());
            orderResponseDto.setTotalAmount(saveOrder.getTotalAmount());
            orderResponseDto.setStatus(saveOrder.getStatus());

            if (saveOrder.getProductType().equals("product")) {
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = productServiceClient.incrementProductStocks(orderResponseDto.getProductId());
                    Long result = productStocks.getBody();
                    log.info("Info log: productStock - {} ", result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving productStock {}", e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }

            } else if (saveOrder.getProductType().equals("preorderProduct")) {
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = productServiceClient.incrementPreorderProductStocks(orderResponseDto.getProductId());
                    Long result = productStocks.getBody();
                    log.info("Info log: productStock - {} ", result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving productStock {}", e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }

            }

            return orderResponseDto;
        }
        return null;
    }
}
