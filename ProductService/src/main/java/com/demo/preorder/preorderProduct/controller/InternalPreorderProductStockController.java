package com.demo.preorder.preorderProduct.controller;

import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.service.PreorderProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/preorderProductStocks")
@RequiredArgsConstructor
public class InternalPreorderProductStockController {
    private final PreorderProductStockService preorderProductStockService;

    @PutMapping("/increment/{preorderProductId}")
    public ResponseEntity<?> incrementCount(@PathVariable("preorderProductId") Long preorderProductId){
        PreorderProductStock preorderProductStock = preorderProductStockService.incrementCount(preorderProductId);
        if(preorderProductStock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(preorderProductStock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }

    @PutMapping("/decrement/{preorderProductId}")
    public ResponseEntity<?> decrementCount(@PathVariable("preorderProductId") Long preorderProductId){
        PreorderProductStock preorderProductStock = preorderProductStockService.decrementCount(preorderProductId);
        if(preorderProductStock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(preorderProductStock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }
}
