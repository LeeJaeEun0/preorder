package com.demo.preorder.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

    private Long productId;

    private Long count;

    private Long totalAmount;

    private String status;
}