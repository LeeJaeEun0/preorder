package com.demo.preorder.product.controller;

import com.demo.preorder.product.dto.ProductStockResponseDto;
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

    @PutMapping("/increment")
    public ResponseEntity<?> incrementCount(@RequestParam("productId") Long productId){
        ProductStockResponseDto productStock = productStockService.incrementCount(productId);
        Long stock = productStock.getStock();
        if(stock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(stock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }

    @PutMapping("/decrement")
    public ResponseEntity<?> decrementCount(@RequestParam("productId") Long productId){
        ProductStockResponseDto productStock = productStockService.decrementCount(productId);
        Long stock = productStock.getStock();
        if(stock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(stock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }
}
