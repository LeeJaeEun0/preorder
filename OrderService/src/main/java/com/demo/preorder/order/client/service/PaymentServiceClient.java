package com.demo.preorder.order.client.service;

import com.demo.preorder.order.client.dto.PaymentResponseDto;
import com.demo.preorder.order.client.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ComponentScan
@FeignClient(name = "PaymentService", url = "${payment.service.url}")
public interface PaymentServiceClient {

    @PostMapping("/api/internal/payments")
    ResponseEntity<PaymentResponseDto> savePayment(@RequestBody PaymentDto paymentDto);

}
