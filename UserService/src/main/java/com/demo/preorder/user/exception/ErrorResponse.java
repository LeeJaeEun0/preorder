package com.demo.preorder.user.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorResponse {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
}
