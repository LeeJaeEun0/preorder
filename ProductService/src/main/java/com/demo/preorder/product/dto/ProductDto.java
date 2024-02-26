package com.demo.preorder.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String title;

    private String content;

    private Long price;

    private Long stock;
}
