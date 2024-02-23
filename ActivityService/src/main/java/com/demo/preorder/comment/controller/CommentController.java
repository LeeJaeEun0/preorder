package com.demo.preorder.comment.controller;

import com.demo.preorder.client.service.UserServiceClient;
import com.demo.preorder.comment.dto.*;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final UserServiceClient userServiceClient;

    @PostMapping("/comment")
    public ResponseEntity<?> saveComment(@RequestHeader Map<String, String> httpHeaders,
                                         @RequestBody CommentDto commentDto) {
        ResponseEntity<Long> responseEntity = userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        CommentResponseDto comment = commentService.saveComment(userId, commentDto);
        if (comment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 작성에 실패했습니다.");
        }
    }

    @PostMapping("/replay")
    public ResponseEntity<?> saveReplay(@RequestHeader Map<String, String> httpHeaders,
                                        @RequestBody CommentReplayDto commentReplayDto) {
        ResponseEntity<Long> responseEntity = userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        CommentResponseDto comment = commentService.saveReplay(userId, commentReplayDto);
        if (comment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("대댓글 작성에 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> selectComment(@RequestParam("postId")Long postId) {
        List<CommentResponseDto> commentList = commentService.selectComment(postId);
        if (commentList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(commentList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 찾기에 실패했습니다.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCommentContent(@RequestHeader Map<String, String> httpHeaders,
                                                  @RequestBody CommentUpdateDto commentUpdateDto) {
        ResponseEntity<Long> responseEntity = userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        log.info("info log = {}", userId);
        log.info("info log = {}", commentUpdateDto.getCommentId());
        log.info("info log = {}", commentUpdateDto.getContent());
        CommentResponseDto comment = commentService.updateCommentContent(userId, commentUpdateDto);

        if (comment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 수정에 실패했습니다.");
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestHeader Map<String, String> httpHeaders,
                                           @PathVariable("commentId") Long commentId) throws Exception {
        ResponseEntity<Long> responseEntity = userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
