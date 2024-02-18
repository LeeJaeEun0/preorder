package com.demo.preorder.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long userId;

    private Long productId;

    private String productType;

    private Long count;

    private Long totalAmount;
}
