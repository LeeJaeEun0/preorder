package com.demo.preorder.payment.service;

import com.demo.preorder.payment.dto.PaymentDto;
import com.demo.preorder.payment.dto.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto savePayment(PaymentDto paymentDto);

    PaymentResponseDto getPaymentById(Long paymentId);

    PaymentResponseDto updatePayment(Long paymentId);
}
