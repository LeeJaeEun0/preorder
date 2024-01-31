package com.demo.preorder.comment.controller;

import com.demo.preorder.comment.dto.GreatCommentDto;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.service.GreatCommentService;
import com.demo.preorder.post.dto.GreatPostDto;
import com.demo.preorder.post.entity.GreatPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/greatComment")
public class GreatCommentController {
    private final GreatCommentService greatCommentService;


    public GreatCommentController(GreatCommentService greatCommentService) {
        this.greatCommentService = greatCommentService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveGreatPost(@PathVariable Long userId, @RequestBody GreatCommentDto greatCommentDto){
        GreatComment greatComment =  greatCommentService.saveGreatComment(userId, greatCommentDto);
        if (greatComment != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(greatComment);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요를 실패했습니다.");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> greatCommentList(@PathVariable Long userId, @RequestBody GreatCommentDto greatCommentDto){
        List<GreatComment> greatCommentList =  greatCommentService.greatCommentList(greatCommentDto);
        if (greatCommentList != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(greatCommentList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요 목록 불러오기를 실패했습니다.");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteCommentPost(@PathVariable Long userId, @RequestBody GreatCommentDto greatCommentDto){
        greatCommentService.deleteGreatComment(userId,greatCommentDto);;
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
