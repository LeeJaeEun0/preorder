package com.demo.preorder.preorderProduct.entity;

import com.demo.preorder.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreorderProductStock implements Serializable { // Serializable 인터페이스를 구현합니다.
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="preorder_product_stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "preorder_product_id")
    private PreorderProduct preorderProductId;

    private Long stock;
}
