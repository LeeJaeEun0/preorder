package com.demo.preorder.payment.client.service;

import com.demo.preorder.order.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ComponentScan
@FeignClient(name = "OrderService", url = "${order.service.url}")
public interface OrderServiceClient {

    @GetMapping("/api/internal/orders")
    ResponseEntity<Order> getOrder(@RequestParam Long orderId);
}
