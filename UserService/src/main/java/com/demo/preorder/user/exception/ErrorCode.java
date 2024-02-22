package com.demo.preorder.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_INPUT("INVALID_INPUT", HttpStatus.BAD_REQUEST, "입력이 유효하지 않습니다."),
    INVALID_EMAIL_AUTHENTICATION("INVALID_EMAIL_AUTHENTICATION", HttpStatus.BAD_REQUEST, "요청된 이메일 인증이 없습니다."),
    INVALID_EMAIL("INVALID_EMAIL", HttpStatus.BAD_REQUEST, "요청한 이메일이 유효하지 않습니다."),
    INVALID_ID("INVALID_ID", HttpStatus.BAD_REQUEST, "요청한 ID가 유효하지 않습니다."),
    INVALID_NUMBER("INVALID_NUMBER", HttpStatus.BAD_REQUEST, "인증번호가 일치 하지 않습니다."),
    INVALID_PASSWORD("INVALID_PASSWORD", HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    EXISTS_EMAIL("EXISTS_EMAIL", HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다. 로그인 해주세요."),
    TRANSMISSION_FAILED("TRANSMISSION_FAILED", HttpStatus.BAD_REQUEST, "이메일 인증 메일 전송을 실패했습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
