package com.demo.preorder.payment.controller;

import com.demo.preorder.payment.dto.PaymentResponseDto;
import com.demo.preorder.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getOrderById(@PathVariable("paymentId") Long paymentId){
        PaymentResponseDto paymentResponseDto = paymentService.getPaymentById(paymentId);
        if (paymentResponseDto  != null) {
            return ResponseEntity.accepted().body(paymentResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 내용을 조회할 수 없습니다");
        }
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<?> updateOrder(@PathVariable("paymentId") Long paymentId){
        PaymentResponseDto paymentResponseDto = paymentService.updatePayment(paymentId);
        if (paymentResponseDto  != null) {
            return ResponseEntity.accepted().body(paymentResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 내용을 수정할 수 없습니다");
        }
    }
}
