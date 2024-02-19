package com.demo.preorder.payment.controller;

import com.demo.preorder.payment.dto.PaymentDto;
import com.demo.preorder.payment.dto.PaymentResponseDto;
import com.demo.preorder.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/payments")
@RequiredArgsConstructor
public class internalPaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> savePayment(@RequestBody PaymentDto paymentDto){
        PaymentResponseDto paymentResponseDto = paymentService.savePayment(paymentDto);
        if(paymentResponseDto != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(paymentResponseDto);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 실패했습니다.");
        }
    }
}
