package com.demo.preorder.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {

    private Long userId;

    private Long orderId;

    private Long productId;

    private String productType;
}
