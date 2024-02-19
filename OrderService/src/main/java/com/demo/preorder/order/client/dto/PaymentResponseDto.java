package com.demo.preorder.order.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    private Long userId;

    private Long orderId;

    private String status;

    private Long productId;
}
