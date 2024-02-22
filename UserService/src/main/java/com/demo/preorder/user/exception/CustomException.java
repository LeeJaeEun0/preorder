package com.demo.preorder.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // RuntimeException의 메시지로 에러 메시지를 설정
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode.getCode(); // ErrorCode 열거형 인스턴스의 코드 문자열을 반환
    }

    // ErrorCode 객체 자체를 반환하는 메서드를 추가할 수도 있습니다.
    public ErrorCode getErrorCodeEnum() {
        return errorCode;
    }
}
