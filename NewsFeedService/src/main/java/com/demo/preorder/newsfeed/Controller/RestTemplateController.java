package com.demo.preorder.newsfeed.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newsfeeds")
@RequiredArgsConstructor
public class RestTemplateController {

    @PostMapping
    public ResponseEntity<?> createNewsfeedFollowing(){
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createNewsfeedFollower(){
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createNewsfeedMyNews(){
        return null;
    }
}
