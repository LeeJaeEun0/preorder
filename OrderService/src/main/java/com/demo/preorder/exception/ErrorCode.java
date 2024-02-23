package com.demo.preorder.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_ORDER("INVALID_ORDER", HttpStatus.BAD_REQUEST, "주문이 유효하지 않습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
