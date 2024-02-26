package com.demo.preorder.preorderProduct.controller;

import com.demo.preorder.client.dto.OrderResponseDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductResponseDto;
import com.demo.preorder.preorderProduct.service.PreorderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preorderProducts")
@RequiredArgsConstructor
public class PreorderProductController {

    private final PreorderProductService preorderProductService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody PreorderProductDto preorderProductDto) {
        PreorderProductResponseDto preorderProduct = preorderProductService.savePreorderProduct(preorderProductDto);
        if (preorderProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(preorderProduct);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 작성에 실패했습니다.");
        }
    }

    @PostMapping("/{userId}/{preorderProductId}")
    public ResponseEntity<?> submitOrder(@PathVariable("userId") Long userId, @PathVariable("preorderProductId") Long preorderProductId) {

        OrderResponseDto orderResponseDto = preorderProductService.submitOrder(userId, preorderProductId);
        if (orderResponseDto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문 요청에 실패했습니다.");

        }
    }

    @GetMapping
    public ResponseEntity<?> findProductAll() {
        List<PreorderProductResponseDto> preorderProductList = preorderProductService.findAllPreorderProduct();
        if (preorderProductList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(preorderProductList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 조회에 실패했습니다.");
        }
    }

    @GetMapping("/{preorderProductId}")
    public ResponseEntity<?> getProductById(@PathVariable("preorderProductId") Long preorderProductId) {
        PreorderProductResponseDto preorderProduct = preorderProductService.getPreorderProductById(preorderProductId);
        if (preorderProduct != null) {
            return ResponseEntity.status(HttpStatus.OK).body(preorderProduct);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 조회에 실패했습니다.");
        }
    }

    @DeleteMapping("/{preorderProductId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("preorderProductId") Long preorderProductId) {
        preorderProductService.deletePreorderProduct(preorderProductId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
