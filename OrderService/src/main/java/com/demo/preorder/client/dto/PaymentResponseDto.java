package com.demo.preorder.client.dto;

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
