package com.demo.preorder.order.controller;

import com.demo.preorder.order.dto.OrderResponseDto;
import com.demo.preorder.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") Long orderId){
        OrderResponseDto orderResponseDto = orderService.getOrderById(orderId);
        if (orderResponseDto  != null) {
            return ResponseEntity.accepted().body(orderResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문을 조회할 수 없습니다");
        }
    }

    @GetMapping("/success/{orderId}")
    public ResponseEntity<?> findOrderSuccessById(@PathVariable("orderId") Long orderId) {
        List<OrderResponseDto> orderResponseDtoList = orderService.findOrderSuccessById(orderId);
        if (orderResponseDtoList  != null) {
            return ResponseEntity.accepted().body(orderResponseDtoList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문을 조회할 수 없습니다");
        }
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<?> findOrderCancelById(@PathVariable("orderId") Long orderId){
        List<OrderResponseDto> orderResponseDtoList = orderService.findOrderCancelById(orderId);
        if (orderResponseDtoList  != null) {
            return ResponseEntity.accepted().body(orderResponseDtoList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문을 조회할 수 없습니다");
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderId") Long orderId){
        OrderResponseDto orderResponseDto = orderService.updateOrder(orderId);
        if (orderResponseDto  != null) {
            return ResponseEntity.accepted().body(orderResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문을 수정할 수 없습니다");
        }
    }

}
