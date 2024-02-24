package com.demo.preorder.product.dto;

import com.demo.preorder.product.entity.ProductStock;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockDto {

    private Long productId;

    private Long stock;

}
