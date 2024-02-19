package com.demo.preorder.payment.service.impl;

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

    @Override
    public PaymentResponseDto savePayment(PaymentDto paymentDto) {
        Random random = new Random();
        // 80%의 사람만 주문한다고 가정
        // 20%의 사람은 변심으로 주문을 취소한다고 가정
        if (random.nextDouble() < 0.8 )  {
            Payment payment = new Payment();
            payment.setUserId(paymentDto.getUserId());
            payment.setOrderId(paymentDto.getOrderId());
            payment.setProductId(paymentDto.getProductId());
            payment.setProductType(paymentDto.getProductType());
            payment.setStatus("success");

            Payment savePayment = paymentDao.savePayment(payment);

            if (savePayment != null) {
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
                paymentResponseDto.setUserId(savePayment.getUserId());
                paymentResponseDto.setOrderId(savePayment.getOrderId());
                paymentResponseDto.setStatus(savePayment.getStatus());
                paymentResponseDto.setProductId(savePayment.getProductId());
                return paymentResponseDto;
            }
        } else {

            Payment payment = new Payment();
            payment.setUserId(paymentDto.getUserId());
            payment.setOrderId(paymentDto.getOrderId());
            payment.setProductId(paymentDto.getProductId());
            payment.setProductType(paymentDto.getProductType());
            payment.setStatus("cancel");

            Payment savePayment = paymentDao.savePayment(payment);

            if (savePayment != null) {
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
                paymentResponseDto.setUserId(savePayment.getUserId());
                paymentResponseDto.setOrderId(savePayment.getOrderId());
                paymentResponseDto.setProductId(savePayment.getProductId());
                paymentResponseDto.setStatus(savePayment.getStatus());
                if(savePayment.getProductType().equals("product")){
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = productServiceClient.incrementProductStocks(savePayment.getProductId());
                        Long result = productStocks.getBody();
                        log.info("Info log: productStock - {} ", result);
                    } catch (Exception e) {
                        // 오류 발생 시 처리
                        log.error("Error saving productStock {}", e.getMessage(), e);
                        // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                    }

                }
                else if (savePayment.getProductType().equals("preorderProduct")) {
                    log.info("{}",savePayment.getProductId());
                    try {
                        // 외부 서비스 호출
                        ResponseEntity<Long> productStocks = productServiceClient.incrementPreorderProductStocks(savePayment.getProductId());
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
            paymentResponseDto.setProductId(payment.getProductId());
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
            paymentResponseDto.setProductId(savePayment.getProductId());
            paymentResponseDto.setStatus(savePayment.getStatus());
            log.info("{}",paymentResponseDto.getProductId());
            if(savePayment.getProductType().equals("product")){
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = productServiceClient.incrementProductStocks(savePayment.getProductId());
                    Long result = productStocks.getBody();
                    log.info("Info log: productStock - {} ", result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving productStock {}", e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }

            }
            else if (savePayment.getProductType().equals("preorderProduct")) {
                try {
                    // 외부 서비스 호출
                    ResponseEntity<Long> productStocks = productServiceClient.incrementPreorderProductStocks(savePayment.getProductId());
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
