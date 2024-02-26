package com.demo.preorder.order.dto;

import com.demo.preorder.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

    private Long productId;

    private Long count;

    private String status;

    public OrderResponseDto(Order order) {
        this.productId = order.getProductId();
        this.count = order.getCount();
        this.status = order.getStatus();
    }
}
