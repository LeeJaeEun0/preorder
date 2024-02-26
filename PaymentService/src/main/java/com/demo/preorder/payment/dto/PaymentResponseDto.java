package com.demo.preorder.payment.dto;

import com.demo.preorder.payment.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    private Long userId;

    private Long orderId;

    private Long productId;

    private String status;


    public PaymentResponseDto(Payment payment) {
        this.userId = payment.getUserId();
        this.orderId = payment.getOrderId();
        this.productId = payment.getProductId();
        this.status = payment.getStatus();

    }
}
