package com.demo.preorder.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ComponentScan
@FeignClient(name = "StockService", url = "${stock.service.url}")
public interface StockServiceClient {
    @PutMapping("/api/internal/preorder-product-stocks/increment")
    ResponseEntity<Long> incrementPreorderProductStocks(@RequestParam Long preorderProductId);

    @PutMapping("/api/internal/product-stocks/increment")
    ResponseEntity<Long> incrementProductStocks(@RequestParam Long productId);
}
