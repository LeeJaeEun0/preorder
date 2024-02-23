package com.demo.preorder.product.dto;

import com.demo.preorder.product.entity.ProductStock;
import lombok.Getter;

@Getter
public class ProductStockResponseDto {

    private String productTitle;

    private Long stock;

    public ProductStockResponseDto(ProductStock productStock){
        this.productTitle = productStock.getProductId().getTitle();
        this.stock = productStock.getStock();
    }
}
