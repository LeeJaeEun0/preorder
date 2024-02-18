package com.demo.preorder.payment.dto;

import com.demo.preorder.order.entity.Order;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {

    private Long userId;

    private Long orderId;

}
