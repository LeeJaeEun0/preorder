package com.demo.preorder.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PRODUCT("INVALID_PRODUCT", HttpStatus.BAD_REQUEST, "상품이 유효하지 않습니다."),
    INVALID_PRODUCT_STOCK("INVALID_PRODUCT_STOCK", HttpStatus.BAD_REQUEST, "상품 재고가 유효하지 않습니다."),
    NOT_EXISTS_PRODUCT_STOCK("NOT_EXISTS_PRODUCT_STOCK", HttpStatus.BAD_REQUEST, "상품 재고가 없습니다."),
    INVALID_PREORDER_PRODUCT("INVALID_PREORDER_PRODUCT", HttpStatus.BAD_REQUEST, "예약 상품이 유효하지 않습니다."),
    INVALID_PREORDER_PRODUCT_STOCK("INVALID_PREORDER_PRODUCT_STOCK", HttpStatus.BAD_REQUEST, "예약 상품 재고가 유효하지 않습니다."),
    NOT_EXISTS_PREORDER_PRODUCT_STOCK("NOT_EXISTS_PREORDER_PRODUCT_STOCK", HttpStatus.BAD_REQUEST, "예약 상품 재고가 없습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
