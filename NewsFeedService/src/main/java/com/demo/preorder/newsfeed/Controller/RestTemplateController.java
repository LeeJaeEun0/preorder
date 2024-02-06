package com.demo.preorder.newsfeed.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/newsfeeds")
@RequiredArgsConstructor
public class RestTemplateController {

    @PostMapping("/follow")
    public ResponseEntity<?> createNewsfeedFollowing(){
//        if(post != null){
//            return  ResponseEntity.status(HttpStatus.CREATED).body(post);
//        }else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("포스트 작성에 실패했습니다.");
//        }
        return null;
    }

    @PostMapping("/following")
    public ResponseEntity<?> createNewsfeedFollower(){
        return null;
    }

    @PostMapping("/myNews")
    public ResponseEntity<?> createNewsfeedMyNews(){
        return null;
    }
}
