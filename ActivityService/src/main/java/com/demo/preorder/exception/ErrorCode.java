package com.demo.preorder.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_ID("INVALID_ID", HttpStatus.BAD_REQUEST, "요청한 ID가 유효하지 않습니다."),
    INVALID_POST("INVALID_POST", HttpStatus.BAD_REQUEST, "요청한 포스트는 유효하지 않습니다."),
    INVALID_COMMENT("INVALID_COMMENT", HttpStatus.BAD_REQUEST, "요청한 댓글 유효하지 않습니다."),
    INVALID_GREAT_POST("INVALID_GREAT_POST", HttpStatus.BAD_REQUEST, "좋아요할 포스트가 유효하지 않습니다."),
    INVALID_GREAT_COMMENT("INVALID_GREAT_COMMENT", HttpStatus.BAD_REQUEST, "좋아요할 댓글이 유효하지 않습니다."),
    DO_NOT_MATCH_ID("DO_NOT_MATCH_ID", HttpStatus.BAD_REQUEST, "작성한 유저 정보와 일치하지 않습니다."),
    NOT_EXISTS_PARENT_COMMENT("NOT_EXISTS_PARENT_COMMENT", HttpStatus.BAD_REQUEST, "부모 댓글이 존재하지 않습니다."),
    NOT_EXISTS_COMMENT("NOT_EXISTS_COMMENT", HttpStatus.BAD_REQUEST, "좋아요할 댓글이 없습니다."),
    NOT_EXISTS_GREAT_POST("NOT_EXISTS_GREAT_POST", HttpStatus.BAD_REQUEST, "좋아요한 포스트가 없습니다."),
    NOT_EXISTS_GREAT_COMMIT("NOT_EXISTS_GREAT_COMMIT", HttpStatus.BAD_REQUEST, "좋아요한 댓글이 없습니다."),
    NOT_EXISTS_FOLLOW("NOT_EXISTS_FOLLOW", HttpStatus.BAD_REQUEST, "팔로우한 기록이 없습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
