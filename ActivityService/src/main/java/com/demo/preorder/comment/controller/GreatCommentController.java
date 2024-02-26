package com.demo.preorder.comment.controller;

import com.demo.preorder.client.service.UserServiceClient;
import com.demo.preorder.comment.dto.GreatCommentDto;
import com.demo.preorder.comment.dto.GreatCommentResponseDto;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.service.GreatCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/greatComments")
@RequiredArgsConstructor
public class GreatCommentController {
    private final GreatCommentService greatCommentService;

    private final UserServiceClient userServiceClient;

    @PostMapping("/{commentId}")
    public ResponseEntity<?> saveGreatPost(@RequestHeader Map<String, String> httpHeaders,
                                           @PathVariable("commentId") Long commentId){
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        log.info("id = {}",userId);
        GreatCommentResponseDto greatComment =  greatCommentService.saveGreatComment(userId, commentId);
        if (greatComment != null) {
            return  ResponseEntity.status(HttpStatus.CREATED).body(greatComment);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요를 실패했습니다.");
        }
    }

    @DeleteMapping("/{greatCommentId}")
    public ResponseEntity<?> deleteCommentPost(@RequestHeader Map<String, String> httpHeaders,
                                               @PathVariable Long greatCommentId){
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        greatCommentService.deleteGreatComment(userId,greatCommentId);;
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
