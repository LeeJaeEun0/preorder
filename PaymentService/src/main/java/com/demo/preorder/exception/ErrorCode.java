package com.demo.preorder.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PAYMENT("INVALID_PAYMENT", HttpStatus.BAD_REQUEST, "결제가 유효하지 않습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
