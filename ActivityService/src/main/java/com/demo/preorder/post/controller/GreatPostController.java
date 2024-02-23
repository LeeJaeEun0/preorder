package com.demo.preorder.post.controller;

import com.demo.preorder.client.service.UserServiceClient;
import com.demo.preorder.post.dto.GreatPostDto;
import com.demo.preorder.post.dto.GreatPostResponseDto;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.service.GreatPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/greatPosts")
@RequiredArgsConstructor
public class GreatPostController {

    private final GreatPostService greatPostService;

    private final UserServiceClient userServiceClient;
    @PostMapping("/{postId}")
    public ResponseEntity<?> saveGreatPost(@RequestHeader Map<String, String> httpHeaders,
                                           @PathVariable("postId") Long postId){
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        GreatPostResponseDto greatPost =  greatPostService.saveGreatPost(userId, postId);

        if (greatPost != null) {
            return  ResponseEntity.status(HttpStatus.CREATED).body(greatPost);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요를 실패했습니다.");
        }
    }

    @GetMapping
    public ResponseEntity<?> greatPostList( @RequestParam("postId") Long postId){
        List<GreatPost> greatPostList =  greatPostService.greatPostList(postId);
        if (greatPostList != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(greatPostList);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요 목록 불러오기를 실패했습니다.");
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteGreatPost(@RequestHeader Map<String, String> httpHeaders,
                                             @PathVariable("postId") Long postId){
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        greatPostService.deleteGreatPost(userId,postId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
