package com.demo.preorder.product.controller;

import com.demo.preorder.product.dto.ProductStockDto;
import com.demo.preorder.product.dto.ProductStockResponseDto;
import com.demo.preorder.product.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/internal/productStocks")
@RequiredArgsConstructor
public class InternalProductStockController {

    private final ProductStockService productStockService;

    @PostMapping
    public ResponseEntity<?> savePreorderStock(@RequestBody ProductStockDto ProductStockDto) {
        ProductStockResponseDto ProductStock = productStockService.saveProduct(ProductStockDto);
        Long stock = ProductStock.getStock();
        if (stock != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(stock);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 생성에 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getProductStockById(@RequestParam("productId") Long productId) {
        ProductStockResponseDto productStock = productStockService.getProductById(productId);
        Long stock = productStock.getStock();
        if (stock != null) {
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 조회 실패했습니다.");
        }
    }

    @PutMapping("/increment")
    public ResponseEntity<?> incrementCount(@RequestParam("productId") Long productId) {
        ProductStockResponseDto productStock = productStockService.incrementCount(productId);
        Long stock = productStock.getStock();
        if (stock != null) {
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }

    @PutMapping("/decrement")
    public ResponseEntity<?> decrementCount(@RequestParam("productId") Long productId) {
        ProductStockResponseDto productStock = productStockService.decrementCount(productId);
        Long stock = productStock.getStock();
        if (stock != null) {
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 감소에 실패했습니다.");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePreorderStock(@RequestParam("productId") Long preorderProductId) {
        productStockService.deleteProduct(preorderProductId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
