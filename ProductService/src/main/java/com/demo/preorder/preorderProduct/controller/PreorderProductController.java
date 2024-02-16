package com.demo.preorder.preorderProduct.controller;

import com.demo.preorder.preorderProduct.dto.PreorderProductDto;
import com.demo.preorder.preorderProduct.dto.PreorderProductUpdateDto;
import com.demo.preorder.preorderProduct.entity.PreorderProduct;
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

    private final PreorderProductService  preorderProductService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody PreorderProductDto preorderProductDto){
        PreorderProduct preorderProduct = preorderProductService.savePreorderProduct(preorderProductDto);
        if(preorderProduct != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(preorderProduct);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 작성에 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> findProductAll(){
        List<PreorderProduct> preorderProductList = preorderProductService.findAllPreorderProduct();
        if(preorderProductList != null){
            return  ResponseEntity.status(HttpStatus.OK).body(preorderProductList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 조회에 실패했습니다.");
        }
    }

    @GetMapping("/{preorderProductId}")
    public ResponseEntity<?> getProductById(@PathVariable("preorderProductId") Long preorderProductId){
        PreorderProduct preorderProduct = preorderProductService.getPreorderProductById(preorderProductId);
        if(preorderProduct != null){
            return  ResponseEntity.status(HttpStatus.OK).body(preorderProduct);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 조회에 실패했습니다.");
        }
    }

    @PutMapping("/{preorderProductId}")
    public ResponseEntity<?> changeProduct(@PathVariable("preorderProductId") Long preorderProductId,@RequestBody PreorderProductUpdateDto preorderProductUpdateDto){
        PreorderProduct preorderProduct = preorderProductService.changePreorderProduct(preorderProductId,preorderProductUpdateDto);
        if(preorderProduct != null){
            return  ResponseEntity.status(HttpStatus.OK).body(preorderProduct);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 수정에 실패했습니다.");
        }
    }

    @DeleteMapping("/{preorderProductId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("preorderProductId") Long preorderProductId){
        preorderProductService.deletePreorderProduct(preorderProductId);
        return  ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
