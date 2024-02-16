package com.demo.preorder.preorderProduct.entity;

import com.demo.preorder.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreorderProductStock {
    @Id
    @Column(name="preorder_product_stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "preorder_product_id")
    private PreorderProduct preorderProductId;

    private Long stock;
}
