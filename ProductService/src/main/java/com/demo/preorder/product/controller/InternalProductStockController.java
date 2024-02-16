package com.demo.preorder.product.controller;

import com.demo.preorder.product.entity.ProductStock;
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

    @PutMapping("/increment/{productId}")
    public ResponseEntity<?> incrementCount(@PathVariable("productId") Long productId, @RequestParam("count") Long count){
        ProductStock productStock = productStockService.incrementCount(productId, count);
        if(productStock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(productStock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }

    @PutMapping("/decrement/{productId}")
    public ResponseEntity<?> decrementCount(@PathVariable("productId") Long productId,@RequestParam("count") Long count){
        ProductStock productStock = productStockService.decrementCount(productId, count);
        if(productStock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(productStock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }
}
