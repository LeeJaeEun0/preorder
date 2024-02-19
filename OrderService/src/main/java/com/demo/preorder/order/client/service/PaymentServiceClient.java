package com.demo.preorder.order.client.service;

import com.demo.preorder.order.client.dto.PaymentDto;
import com.demo.preorder.order.client.dto.PaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@ComponentScan
@FeignClient(name = "PaymentService", url = "${payment.service.url}")
public interface PaymentServiceClient {

    @PostMapping("/api/internal/payments")
    ResponseEntity<PaymentResponseDto> savePayment(@RequestBody PaymentDto paymentDto);

}
