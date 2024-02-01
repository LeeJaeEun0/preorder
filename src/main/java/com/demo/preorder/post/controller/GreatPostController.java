package com.demo.preorder.post.controller;

import com.demo.preorder.post.dto.GreatPostDto;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.service.GreatPostService;
import com.demo.preorder.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/greatPosts")
public class GreatPostController {

    private final GreatPostService greatPostService;

    private final UserService userService;

    public GreatPostController(GreatPostService greatPostService, UserService userService) {
        this.greatPostService = greatPostService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveGreatPost(@RequestHeader Map<String, String> httpHeaders,
                                           @RequestBody GreatPostDto greatPostDto){
        Long userId = userService.findUserId(httpHeaders);
        GreatPost greatPost =  greatPostService.saveGreatPost(userId, greatPostDto);

        if (greatPost != null) {
            return  ResponseEntity.status(HttpStatus.CREATED).body(greatPost);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요를 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> greatPostList( @RequestBody GreatPostDto greatPostDto){
        List<GreatPost> greatPostList =  greatPostService.greatPostList(greatPostDto);
        if (greatPostList != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(greatPostList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요 목록 불러오기를 실패했습니다.");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGreatPost(@RequestHeader Map<String, String> httpHeaders,
                                             @RequestBody GreatPostDto greatPostDto){
        Long userId = userService.findUserId(httpHeaders);
        greatPostService.deleteGreatPost(userId,greatPostDto);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
