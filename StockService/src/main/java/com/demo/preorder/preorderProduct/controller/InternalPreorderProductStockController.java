package com.demo.preorder.preorderProduct.controller;

import com.demo.preorder.preorderProduct.dto.PreorderProductStockDto;
import com.demo.preorder.preorderProduct.entity.PreorderProductStock;
import com.demo.preorder.preorderProduct.service.PreorderProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/preorder-product-stocks")
@RequiredArgsConstructor
public class InternalPreorderProductStockController {
    private final PreorderProductStockService preorderProductStockService;

    @PostMapping
    public ResponseEntity<?> savePreorderStock(@RequestBody PreorderProductStockDto preorderProductStockDto) {
        PreorderProductStock preorderProductStock = preorderProductStockService.savePreorderProduct(preorderProductStockDto);
        Long stock = preorderProductStock.getStock();
        if (stock != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(stock);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 생성에 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getProductStockById(@RequestParam("preorderProductId") Long preorderProductId) {
        PreorderProductStock preorderProductStock = preorderProductStockService.getPreorderProductById(preorderProductId);
        Long stock = preorderProductStock.getStock();
        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }

    @PutMapping("/increment")
    public ResponseEntity<?> incrementCount(@RequestParam("preorderProductId") Long preorderProductId) {
        PreorderProductStock preorderProductStock = preorderProductStockService.incrementCount(preorderProductId);
        Long stock = preorderProductStock.getStock();
        if (stock != null) {
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 증가에 실패했습니다.");
        }
    }

    @PutMapping("/decrement")
    public ResponseEntity<?> decrementCount(@RequestParam("preorderProductId") Long preorderProductId) {
        PreorderProductStock preorderProductStock = preorderProductStockService.decrementCount(preorderProductId);
        Long stock = preorderProductStock.getStock();
        if (stock != null) {
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("재고 감소에 실패했습니다.");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePreorderStock(@RequestParam("preorderProductId") Long preorderProductId) {
        preorderProductStockService.deletePreorderProduct(preorderProductId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
