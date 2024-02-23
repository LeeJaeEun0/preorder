package com.demo.preorder.exception;

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
    NOT_EXISTS_PROFILE("NOT_EXISTS_PROFILE", HttpStatus.BAD_REQUEST, "프로필 사진이 없습니다."),
    TRANSMISSION_FAILED("TRANSMISSION_FAILED", HttpStatus.BAD_REQUEST, "이메일 인증 메일 전송을 실패했습니다."),
    JSON_PARSE_EXCEPTION("JSON_PARSE_EXCEPTION",HttpStatus.BAD_REQUEST, "JsonParseException"),
    JWT_EXCEPTION("JWT_EXCEPTION",HttpStatus.UNAUTHORIZED,"인증 오류 발생 했습니다."),
    JWT_TOKEN_EXPIRED("JWT_TOKEN_EXPIRED",HttpStatus.FORBIDDEN, "토큰이 만료 되었습니다."),
    AUTHORIZATION_EXCEPTION("AUTHORIZATION_EXCEPTION", HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    FAIL_USER_VERIFY("FAIL_USER_VERIFY",HttpStatus.BAD_REQUEST, "Fail User Verify");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
