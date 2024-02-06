package com.demo.preorder.newsfeed.Controller;

import com.demo.preorder.client.service.NewsfeedClient;
import com.demo.preorder.newsfeed.entity.NewsfeedFollower;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedFollowerService;
import com.demo.preorder.newsfeed.service.NewsfeedFollowingService;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/newsfeeds")
@RequiredArgsConstructor
public class NewsfeedController {

    private final NewsfeedFollowingService newsfeedFollowingService;

    private final NewsfeedFollowerService newsfeedFollowerService;

    private final NewsfeedMyNewsService newsfeedMyNewsService;

    private final NewsfeedClient newsfeedClient;

    @GetMapping("/following")
    public ResponseEntity<?> selectNewsfeedIFollow(@RequestHeader Map<String, String> httpHeaders){
        Long userId = newsfeedClient.findUserId(httpHeaders);
        List<NewsfeedFollowing> newsfeedFollowingList = newsfeedFollowingService.newsfeedFollowing(userId);
        if (newsfeedFollowingList != null) {
            return ResponseEntity.accepted().body(newsfeedFollowingList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }

    @GetMapping("/follower")
    public ResponseEntity<?> selectNewsfeedFollowedMe(@RequestHeader Map<String, String> httpHeaders){
        Long userId = newsfeedClient.findUserId(httpHeaders);
        List<NewsfeedFollower> newsfeedFollowerList = newsfeedFollowerService.newsfeedFollower(userId);
        if (newsfeedFollowerList != null) {
            return ResponseEntity.accepted().body(newsfeedFollowerList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }

    @GetMapping("/mynews")
    public ResponseEntity<?> selectNewsfeedMyNews(@RequestHeader Map<String, String> httpHeaders){
        Long userId = newsfeedClient.findUserId(httpHeaders);
        List<NewsfeedMyNews> newsfeedFollowedMeList = newsfeedMyNewsService.newsfeedMyNews(userId);
        if (newsfeedFollowedMeList != null) {
            return ResponseEntity.accepted().body(newsfeedFollowedMeList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }
}
