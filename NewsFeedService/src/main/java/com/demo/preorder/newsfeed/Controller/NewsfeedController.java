package com.demo.preorder.newsfeed.Controller;

import com.demo.preorder.client.service.NewsfeedClient;
import com.demo.preorder.newsfeed.entity.Newsfeed;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import com.demo.preorder.newsfeed.service.NewsfeedService;
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

    private final NewsfeedService newsfeedService;

    private final NewsfeedMyNewsService newsfeedMyNewsService;

    private final NewsfeedClient newsfeedClient;

    @GetMapping
    public ResponseEntity<?> selectNewsfeed(@RequestHeader Map<String, String> httpHeaders){
        Long userId = newsfeedClient.findUserId(httpHeaders);
        List<Newsfeed> newsfeedList = newsfeedService.findNewsfeed(userId);
        if (newsfeedList != null) {
            return ResponseEntity.accepted().body(newsfeedList);
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
