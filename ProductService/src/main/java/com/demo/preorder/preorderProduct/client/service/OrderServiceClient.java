package com.demo.preorder.preorderProduct.client.service;

import com.demo.preorder.preorderProduct.client.dto.OrderDto;
import com.demo.preorder.preorderProduct.client.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@ComponentScan
@FeignClient(name = "orderService", url = "${order.service.url}")
public interface OrderServiceClient {
    @PostMapping("/api/internal/orders")
    ResponseEntity<OrderResponseDto> saveOrder(@RequestBody OrderDto orderDto);
}
