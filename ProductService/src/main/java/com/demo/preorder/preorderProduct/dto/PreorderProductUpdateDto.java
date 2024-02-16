package com.demo.preorder.preorderProduct.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class PreorderProductUpdateDto {
    private String title;
    private String content;
    private Long price;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime availableFrom;
    private Long stock;
}
