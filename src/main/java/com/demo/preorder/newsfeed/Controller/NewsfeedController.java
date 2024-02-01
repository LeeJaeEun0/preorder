package com.demo.preorder.newsfeed.Controller;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedFollowedMeService;
import com.demo.preorder.newsfeed.service.NewsfeedIFollowService;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/newsfeeds")
public class NewsfeedController {
    private final NewsfeedIFollowService newsfeedIFollowService;

    private final NewsfeedFollowedMeService newsfeedFollowedMeService;

    private final NewsfeedMyNewsService newsfeedMyNewsService;

    public NewsfeedController(NewsfeedIFollowService newsfeedIFollowService, NewsfeedFollowedMeService newsfeedFollowedMeService, NewsfeedMyNewsService newsfeedMyNewsService) {
        this.newsfeedIFollowService = newsfeedIFollowService;
        this.newsfeedFollowedMeService = newsfeedFollowedMeService;
        this.newsfeedMyNewsService = newsfeedMyNewsService;
    }

    @GetMapping("/ifollow/{userId}")
    public ResponseEntity<?> selectNewsfeedIFollow(@PathVariable Long userId){
        List<NewsfeedIFollow> newsfeedIFollowList = newsfeedIFollowService.newsfeedIFollow(userId);
        if (newsfeedIFollowList != null) {
            return ResponseEntity.accepted().body(newsfeedIFollowList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }

    @GetMapping("/followed/{userId}")
    public ResponseEntity<?> selectNewsfeedFollowedMe(@PathVariable Long userId){
        List<NewsfeedFollowedMe> newsfeedFollowedMeList = newsfeedFollowedMeService.newsfeedFollowedMe(userId);
        if (newsfeedFollowedMeList != null) {
            return ResponseEntity.accepted().body(newsfeedFollowedMeList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }

    @GetMapping("/mynews/{userId}")
    public ResponseEntity<?> selectNewsfeedMyNews(@PathVariable Long userId){
        List<NewsfeedMyNews> newsfeedFollowedMeList = newsfeedMyNewsService.newsfeedMyNews(userId);
        if (newsfeedFollowedMeList != null) {
            return ResponseEntity.accepted().body(newsfeedFollowedMeList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }
}
