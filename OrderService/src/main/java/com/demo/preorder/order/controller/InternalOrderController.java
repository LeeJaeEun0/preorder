package com.demo.preorder.order.controller;

import com.demo.preorder.order.dao.OrderDao;
import com.demo.preorder.order.dto.OrderDto;
import com.demo.preorder.order.dto.OrderResponseDto;
import com.demo.preorder.order.entity.Order;
import com.demo.preorder.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/orders")
@RequiredArgsConstructor
public class InternalOrderController {

    private final OrderService orderService;

    private final OrderDao orderDao;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderDto orderDto){
        OrderResponseDto orderResponseDto = orderService.saveOrder(orderDto);
        if(orderResponseDto != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문 작성에 실패했습니다.");
        }
    }

}
