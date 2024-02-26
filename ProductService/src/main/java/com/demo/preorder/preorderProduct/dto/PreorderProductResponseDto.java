package com.demo.preorder.preorderProduct.dto;

import com.demo.preorder.preorderProduct.entity.PreorderProduct;
import lombok.Getter;

@Getter
public class PreorderProductResponseDto {

    private String title;

    private String content;

    private Long price;

    public PreorderProductResponseDto(PreorderProduct preorderProduct){
        this.title = preorderProduct.getTitle();
        this.content = preorderProduct.getContent();
        this.price = preorderProduct.getPrice();
    }

}
