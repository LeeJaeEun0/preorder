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
    @PostMapping("/api/internal/preorder-product-stocks")
    ResponseEntity<Long> savePreorderProductStocks(@RequestBody PreorderProductStockDto preorderProductStockDto);

    @GetMapping("/api/internal/preorder-product-stocks")
    ResponseEntity<Long> getPreorderProductStock(@RequestParam("preorderProductId") Long preorderProductId);

    @PutMapping("/api/internal/preorder-product-stocks/decrement")
    ResponseEntity<Long> decrementPreorderProductStocks(@RequestParam("preorderProductId") Long preorderProductId);

    @DeleteMapping("/api/internal/preorder-product-stocks")
    ResponseEntity<Void> deletePreorderProductStocks(@RequestParam("preorderProductId") Long preorderProductId);

    @PostMapping("/api/internal/product-stocks")
    ResponseEntity<Long> saveProductStocks(@RequestBody ProductStockDto ProductStockDto);

    @GetMapping("/api/internal/product-stocks")
    ResponseEntity<Long> getProductStock(@RequestParam("productId") Long productId);

    @PutMapping("/api/internal/product-stocks/decrement")
    ResponseEntity<Long> decrementProductStocks(@RequestParam("productId") Long productId);

    @DeleteMapping("/api/internal/product-stocks")
    ResponseEntity<Void> deleteProductStocks(@RequestParam("productId") Long productId);

}
