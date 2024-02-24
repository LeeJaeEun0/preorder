package com.demo.preorder.product.dto;

import com.demo.preorder.product.entity.ProductStock;
import lombok.Getter;

@Getter
public class ProductStockResponseDto {

    private Long productId;

    private Long stock;

    public ProductStockResponseDto(ProductStock productStock){
        this.productId = productStock.getProductId();
        this.stock = productStock.getStock();
    }
}
