package com.demo.preorder.payment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long orderId;

    private Long productId;

    private String productType;

    private String status;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "created_date", nullable = true, insertable = true, updatable = false)
    private LocalDate createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "lastmodified_date", nullable = true, insertable = true, updatable = true)
    private LocalDate lastmodifiedDate;

    @PrePersist
    public void setDate() {
        this.setCreatedDate(LocalDate.now());
        this.setLastmodifiedDate(LocalDate.now());
    }

    @PreUpdate
    public void modifiedDate() {
        this.setLastmodifiedDate(LocalDate.now());
    }
}
