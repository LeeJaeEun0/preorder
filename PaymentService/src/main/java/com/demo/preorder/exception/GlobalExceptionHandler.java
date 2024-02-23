package com.demo.preorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        // 에러 응답 생성
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setTimestamp(LocalDateTime.now());

        // 에러 코드에 따라 다른 상태 코드를 반환할 수 있습니다
        HttpStatus status = HttpStatus.BAD_REQUEST; // 기본값으로 400 Bad Request 설정

        // ResponseEntity로 에러 응답 반환
        return new ResponseEntity<>(errorResponse, status);
    }
}
