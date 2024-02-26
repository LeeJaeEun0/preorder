package com.demo.preorder.order.service.impl;

import com.demo.preorder.client.dto.PaymentDto;
import com.demo.preorder.client.dto.PaymentResponseDto;
import com.demo.preorder.client.service.PaymentServiceClient;
import com.demo.preorder.client.service.StockServiceClient;
import com.demo.preorder.order.dao.OrderDao;
import com.demo.preorder.order.dto.OrderDto;
import com.demo.preorder.order.dto.OrderResponseDto;
import com.demo.preorder.order.entity.Order;
import com.demo.preorder.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    private final StockServiceClient stockServiceClient;

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
            order.setStatus("success");

            Order saveOrder = orderDao.saveOrder(order);

            if (saveOrder != null) {
                OrderResponseDto orderResponseDto = new OrderResponseDto(saveOrder);

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
                } catch (HttpClientErrorException | HttpServerErrorException e) {
                    log.error("HTTP 오류 발생, 주문 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage());
                    throw e;
                } catch (Exception e) {
                    log.error("결제 진행 중 예외 발생, 주문 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage(), e);
                    throw e;
                }


                return orderResponseDto;
            }
        } else {

            Order order = new Order();
            order.setUserId(orderDto.getUserId());
            order.setProductId(orderDto.getProductId());
            order.setProductType(orderDto.getProductType());
            order.setCount(orderDto.getCount());
            order.setStatus("cancel");

            Order saveOrder = orderDao.saveOrder(order);

            if (saveOrder != null) {
                OrderResponseDto orderResponseDto = new OrderResponseDto(saveOrder);

                if (saveOrder.getProductType().equals("product")) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = stockServiceClient.incrementProductStocks(orderDto.getProductId());
                        Long result = productStocks.getBody();
                        log.info("Info log: productStock - {} ", result);
                    } catch (HttpClientErrorException | HttpServerErrorException e) {
                        log.error("HTTP 오류 발생, 주문취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage());
                        throw e;
                    } catch (Exception e) {
                        log.error("결제 취소 중 예외 발생, 주문취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage(), e);
                        throw e;
                    }

                } else if (saveOrder.getProductType().equals("preorderProduct")) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = stockServiceClient.incrementPreorderProductStocks(orderDto.getProductId());
                        Long result = productStocks.getBody();
                        log.info("Info log: productStock - {} ", result);
                    } catch (HttpClientErrorException | HttpServerErrorException e) {
                        log.error("HTTP 오류 발생, 주문 취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage());
                        throw e;
                    } catch (Exception e) {
                        log.error("결제 취소 중 예외 발생, 주문 취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage(), e);
                        throw e;
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
        return new OrderResponseDto(order);


    }

    @Override
    public List<OrderResponseDto> findOrderSuccessById(Long productId, String productType) {
        List<Order> orderList = orderDao.findOrderSuccessById(productId, productType);

        return orderList.stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> findOrderCancelById(Long productId, String productType) {
        List<Order> orderList = orderDao.findOrderCancelById(productId, productType);

        return orderList.stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());

    }

    @Override
    public OrderResponseDto updateOrder(Long orderId) {
        Order saveOrder = orderDao.updateOrder(orderId);
        OrderResponseDto orderResponseDto = new OrderResponseDto(saveOrder);

        if (saveOrder.getProductType().equals("product")) {
            try {
                // 외부 서비스 호출
                ResponseEntity<Long> productStocks = stockServiceClient.incrementProductStocks(orderResponseDto.getProductId());
                Long result = productStocks.getBody();
                log.info("Info log: productStock - {} ", result);
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                log.error("HTTP 오류 발생, 주문 취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage());
                throw e;
            } catch (Exception e) {
                log.error("결제 취소 중 예외 발생, 주문 취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage(), e);
                throw e;
            }

        } else if (saveOrder.getProductType().equals("preorderProduct")) {
            try {
                // 외부 서비스 호출
                ResponseEntity<Long> productStocks = stockServiceClient.incrementPreorderProductStocks(orderResponseDto.getProductId());
                Long result = productStocks.getBody();
                log.info("Info log: productStock - {} ", result);
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                log.error("HTTP 오류 발생, 주문 취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage());
                throw e;
            } catch (Exception e) {
                log.error("결제 취소 중 예외 발생, 주문 취소 ID: {}, 오류 메시지: {}", saveOrder.getId(), e.getMessage(), e);
                throw e;
            }

        }

        return orderResponseDto;
    }
}
