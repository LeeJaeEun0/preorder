package com.demo.preorder.comment.controller;

import com.demo.preorder.comment.dto.CommentDeleteDto;
import com.demo.preorder.comment.dto.CommentDto;
import com.demo.preorder.comment.dto.CommentReplayDto;
import com.demo.preorder.comment.dto.CommentUpdateDto;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/{userId}")
    public ResponseEntity<?> saveComment(@PathVariable Long userId, @RequestBody CommentDto commentDto){
        Comment comment = commentService.saveComment(userId, commentDto);
        if(comment != null){
            return  ResponseEntity.status(HttpStatus.OK).body(comment);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 작성에 실패했습니다.");
        }
    }
    @PostMapping("/replay/{userId}")
    public ResponseEntity<?> insertComment(@PathVariable Long userId,@RequestBody CommentReplayDto commentReplayDto){
        Comment comment = commentService.insertComment(userId, commentReplayDto);
        if(comment != null){
            return  ResponseEntity.status(HttpStatus.OK).body(comment);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("대댓글 작성에 실패했습니다.");
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> selectComment(@PathVariable Long userId,@RequestBody CommentDto commentDto){
        List<Comment> commentList = commentService.selectComment(commentDto);
        if(commentList != null){
            return  ResponseEntity.status(HttpStatus.OK).body(commentList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 찾기에 실패했습니다.");
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> changeCommentContent(@PathVariable Long userId,@RequestBody CommentUpdateDto commentUpdateDto){
        Comment comment = commentService.changeCommentContent(userId, commentUpdateDto);
        if(comment != null){
            return  ResponseEntity.status(HttpStatus.OK).body(comment);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 수정에 실패했습니다.");
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long userId,@RequestBody CommentDeleteDto commentDeleteDto) throws Exception {
        commentService.deleteComment(userId,commentDeleteDto);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
