package com.demo.preorder.product.controller;

import com.demo.preorder.product.dto.ProductDto;
import com.demo.preorder.product.dto.ProductUpdateDto;
import com.demo.preorder.product.entity.Product;
import com.demo.preorder.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        Product product = productService.saveProduct(productDto);
        if(product != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 작성에 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> findProductAll(){
        List<Product> productList = productService.findAllProduct();
        if(productList != null){
            return  ResponseEntity.status(HttpStatus.OK).body(productList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 작성에 실패했습니다.");
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") Long productId){
        Product product = productService.getProductById(productId);
        if(product != null){
            return  ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 작성에 실패했습니다.");
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> changeProduct(@PathVariable("productId") Long productId,@RequestBody ProductUpdateDto productUpdateDto){
        Product product = productService.changeProduct(productId,productUpdateDto);
        if(product != null){
            return  ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매글 작성에 실패했습니다.");
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        return  ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
