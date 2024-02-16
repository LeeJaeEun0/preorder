package com.demo.preorder.product.controller;

import com.demo.preorder.product.entity.ProductStock;
import com.demo.preorder.product.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productStocks")
@RequiredArgsConstructor
public class ProductStockController {

    private final ProductStockService productStockService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> saveProductStock(@PathVariable("productId") Long productId){
        ProductStock productStock = productStockService.getProductById(productId);
        if(productStock != null){
            return  ResponseEntity.status(HttpStatus.OK).body(productStock);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 조회 실패했습니다.");
        }
    }

}
