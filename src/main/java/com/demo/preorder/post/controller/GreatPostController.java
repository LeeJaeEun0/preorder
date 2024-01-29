package com.demo.preorder.post.controller;

import com.demo.preorder.post.dto.GreatPostDto;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.service.GreatPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greatPosts")
public class GreatPostController {

    private final GreatPostService greatPostService;

    public GreatPostController(GreatPostService greatPostService) {
        this.greatPostService = greatPostService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveGreatPost(@PathVariable Long userId, @RequestBody GreatPostDto greatPostDto){
        GreatPost greatPost =  greatPostService.saveGreatPost(userId, greatPostDto);
        if (greatPost != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(greatPost);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요를 실패했습니다.");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> greatPostList(@PathVariable Long userId, @RequestBody GreatPostDto greatPostDto){
        List<GreatPost> greatPostList =  greatPostService.greatPostList(greatPostDto);
        if (greatPostList != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(greatPostList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요 목록 불러오기를 실패했습니다.");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteGreatPost(@PathVariable Long userId, @RequestBody GreatPostDto greatPostDto){
        greatPostService.deleteGreatPost(userId,greatPostDto);;
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
