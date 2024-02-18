package com.demo.preorder.payment.service.impl;

import com.demo.preorder.order.entity.Order;
import com.demo.preorder.payment.client.service.OrderServiceClient;
import com.demo.preorder.payment.client.service.ProductServiceClient;
import com.demo.preorder.payment.dao.PaymentDao;
import com.demo.preorder.payment.dto.PaymentDto;
import com.demo.preorder.payment.dto.PaymentResponseDto;
import com.demo.preorder.payment.entity.Payment;
import com.demo.preorder.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;

    private final ProductServiceClient productServiceClient;

    private final OrderServiceClient orderServiceClient;

    @Override
    public PaymentResponseDto savePayment(PaymentDto paymentDto) {

        Order order = new Order();
        try {
            // 외부 서비스 호출
            ResponseEntity<Order> orderResponseEntity = orderServiceClient.getOrder(paymentDto.getOrderId());
            order = orderResponseEntity.getBody();
            log.info("Info log: productStock - {} ", order);
        } catch (Exception e) {
            // 오류 발생 시 처리
            log.error("Error saving productStock {}", e.getMessage(), e);
            // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
        }

        Random random = new Random();
        // 80%의 사람만 주문한다고 가정
        // 20%의 사람은 변심으로 주문을 취소한다고 가정
        if (random.nextDouble() < 0.8 && order != null)  {
            Payment payment = new Payment();
            payment.setUserId(paymentDto.getUserId());
            payment.setOrderId(order);
            payment.setStatus("success");

            Payment savePayment = paymentDao.savePayment(payment);

            if (savePayment != null) {
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
                paymentResponseDto.setUserId(savePayment.getUserId());
                paymentResponseDto.setOrderId(savePayment.getOrderId());
                paymentResponseDto.setStatus(savePayment.getStatus());
                return paymentResponseDto;
            }
        } else {

            Payment payment = new Payment();
            payment.setUserId(paymentDto.getUserId());
            payment.setOrderId(order);
            payment.setStatus("cancel");

            Payment savePayment = paymentDao.savePayment(payment);

            if (savePayment != null) {
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
                paymentResponseDto.setUserId(savePayment.getUserId());
                paymentResponseDto.setOrderId(savePayment.getOrderId());
                paymentResponseDto.setStatus(savePayment.getStatus());

                if(savePayment.getOrderId().getProductType().equals("product")){
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = productServiceClient.incrementProductStocks(paymentResponseDto.getOrderId().getProductId());
                        Long result = productStocks.getBody();
                        log.info("Info log: productStock - {} ", result);
                    } catch (Exception e) {
                        // 오류 발생 시 처리
                        log.error("Error saving productStock {}", e.getMessage(), e);
                        // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                    }

                }
                else if (savePayment.getOrderId().getProductType().equals("preorderProduct")) {
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = productServiceClient.incrementPreorderProductStocks(paymentResponseDto.getOrderId().getProductId());
                        Long result = productStocks.getBody();
                        log.info("Info log: productStock - {} ", result);
                    } catch (Exception e) {
                        // 오류 발생 시 처리
                        log.error("Error saving productStock {}", e.getMessage(), e);
                        // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                    }

                }

                return paymentResponseDto;
            }
        }
        return null;

    }

    @Override
    public PaymentResponseDto getPaymentById(Long paymentId) {
        Payment payment = paymentDao.getPaymentById(paymentId);

        if(payment != null){
            PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
            paymentResponseDto.setUserId(payment.getUserId());
            paymentResponseDto.setOrderId(payment.getOrderId());
            paymentResponseDto.setStatus(payment.getStatus());
            return paymentResponseDto;
        }
        return null;
    }

    @Override
    public PaymentResponseDto updatePayment(Long paymentId) {
        Payment savePayment = paymentDao.updatePayment(paymentId);

        if(savePayment != null){
            PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
            paymentResponseDto.setUserId(savePayment.getUserId());
            paymentResponseDto.setOrderId(savePayment.getOrderId());
            paymentResponseDto.setStatus(savePayment.getStatus());

            if(savePayment.getOrderId().getProductType().equals("product")){
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = productServiceClient.incrementProductStocks(paymentResponseDto.getOrderId().getProductId());
                    Long result = productStocks.getBody();
                    log.info("Info log: productStock - {} ", result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving productStock {}", e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }

            }
            else if (savePayment.getOrderId().getProductType().equals("preorderProduct")) {
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = productServiceClient.incrementPreorderProductStocks(paymentResponseDto.getOrderId().getProductId());
                    Long result = productStocks.getBody();
                    log.info("Info log: productStock - {} ", result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving productStock {}", e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }

            }

            return paymentResponseDto;
        }
        return null;
    }
}
