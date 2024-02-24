package com.demo.preorder.client.service;

import com.demo.preorder.client.dto.PreorderProductStockDto;
import com.demo.preorder.client.dto.ProductStockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan
@FeignClient(name = "StorkService", url = "${stork.service.url}")
public interface StockServiceClient {
    @PostMapping("/api/internal/preorderProductStocks")
    ResponseEntity<Long> savePreorderProductStocks(@RequestBody PreorderProductStockDto preorderProductStockDto);

    @GetMapping("/api/internal/preorderProductStocks")
    ResponseEntity<Long> getPreorderProductStock(@RequestParam("preorderProductId") Long preorderProductId);

    @PutMapping("/api/internal/preorderProductStocks/decrement")
    ResponseEntity<Long> decrementPreorderProductStocks(@RequestParam("preorderProductId") Long preorderProductId);

    @DeleteMapping("/api/internal/preorderProductStocks")
    ResponseEntity<Void> deletePreorderProductStocks(@RequestParam("preorderProductId") Long preorderProductId);

    @PostMapping("/api/internal/productStocks")
    ResponseEntity<Long> saveProductStocks(@RequestBody ProductStockDto ProductStockDto);

    @GetMapping("/api/internal/productStocks")
    ResponseEntity<Long> getProductStock(@RequestParam("productId") Long productId);

    @PutMapping("/api/internal/productStocks/decrement")
    ResponseEntity<Long> decrementProductStocks(@RequestParam("productId") Long productId);

    @DeleteMapping("/api/internal/productStocks")
    ResponseEntity<Void> deleteProductStocks(@RequestParam("productId") Long productId);

}
