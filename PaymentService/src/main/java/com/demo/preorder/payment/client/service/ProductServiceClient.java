package com.demo.preorder.payment.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ComponentScan
@FeignClient(name = "ProductService", url = "${product.service.url}")
public interface ProductServiceClient {
    @PutMapping("/api/internal/preorderProductStocks/increment")
    ResponseEntity<Long> incrementPreorderProductStocks(@RequestParam Long productId);

    @PutMapping("/api/internal/productStocks/increment")
    ResponseEntity<Long> incrementProductStocks(@RequestParam Long productId);
}
