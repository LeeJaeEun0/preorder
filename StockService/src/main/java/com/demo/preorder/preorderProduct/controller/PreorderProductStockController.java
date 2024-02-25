package com.demo.preorder.preorderProduct.controller;

import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.service.PreorderProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/preorderProductStocks")
@RequiredArgsConstructor
public class PreorderProductStockController {
    private final PreorderProductStockService preorderProductStockService;
    @GetMapping("/{preorderProductId}")
    public ResponseEntity<?> getProductStock(@PathVariable("preorderProductId") Long preorderProductId){
        PreorderProductStock preorderProductStock = preorderProductStockService.getPreorderProductById(preorderProductId);
        if(preorderProductStock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(preorderProductStock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 조회 실패했습니다.");
        }
    }
}
