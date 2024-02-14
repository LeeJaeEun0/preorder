package com.demo.preorder.comment.controller;

import com.demo.preorder.client.service.UserServiceClient;
import com.demo.preorder.comment.dto.GreatCommentDto;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.service.GreatCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/greatComment")
@RequiredArgsConstructor
public class GreatCommentController {
    private final GreatCommentService greatCommentService;

    private final UserServiceClient userServiceClient;

    @PostMapping
    public ResponseEntity<?> saveGreatPost(@RequestHeader Map<String, String> httpHeaders,
                                           @RequestBody GreatCommentDto greatCommentDto){
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        GreatComment greatComment =  greatCommentService.saveGreatComment(userId, greatCommentDto);
        if (greatComment != null) {
            return  ResponseEntity.status(HttpStatus.CREATED).body(greatComment);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요를 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> greatCommentList(@RequestBody GreatCommentDto greatCommentDto){
        List<GreatComment> greatCommentList =  greatCommentService.greatCommentList(greatCommentDto);
        if (greatCommentList != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(greatCommentList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요 목록 불러오기를 실패했습니다.");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCommentPost(@RequestHeader Map<String, String> httpHeaders,
                                               @RequestBody GreatCommentDto greatCommentDto){
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        greatCommentService.deleteGreatComment(userId,greatCommentDto);;
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
