package com.demo.preorder.product.dto;

import com.demo.preorder.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private String title;

    private String content;

    private Long price;

    public ProductResponseDto(Product product) {
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
    }

}
