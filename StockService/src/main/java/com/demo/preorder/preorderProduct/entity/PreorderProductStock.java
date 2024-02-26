package com.demo.preorder.preorderProduct.entity;

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
    @Column(name = "preorder_product_stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long preorderProductId;

    private Long stock;
}
