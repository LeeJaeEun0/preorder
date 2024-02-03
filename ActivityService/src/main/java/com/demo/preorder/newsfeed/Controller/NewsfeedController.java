package com.demo.preorder.newsfeed.Controller;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedFollowedMeService;
import com.demo.preorder.newsfeed.service.NewsfeedIFollowService;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import com.demo.preorder.user.service.UserService;
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

    private final NewsfeedIFollowService newsfeedIFollowService;

    private final NewsfeedFollowedMeService newsfeedFollowedMeService;

    private final NewsfeedMyNewsService newsfeedMyNewsService;

    private final UserService userService;

    @GetMapping("/ifollow")
    public ResponseEntity<?> selectNewsfeedIFollow(@RequestHeader Map<String, String> httpHeaders){
        Long userId = userService.findUserId(httpHeaders);
        List<NewsfeedIFollow> newsfeedIFollowList = newsfeedIFollowService.newsfeedIFollow(userId);
        if (newsfeedIFollowList != null) {
            return ResponseEntity.accepted().body(newsfeedIFollowList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }

    @GetMapping("/followed")
    public ResponseEntity<?> selectNewsfeedFollowedMe(@RequestHeader Map<String, String> httpHeaders){
        Long userId = userService.findUserId(httpHeaders);
        List<NewsfeedFollowedMe> newsfeedFollowedMeList = newsfeedFollowedMeService.newsfeedFollowedMe(userId);
        if (newsfeedFollowedMeList != null) {
            return ResponseEntity.accepted().body(newsfeedFollowedMeList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }

    @GetMapping("/mynews")
    public ResponseEntity<?> selectNewsfeedMyNews(@RequestHeader Map<String, String> httpHeaders){
        Long userId = userService.findUserId(httpHeaders);
        List<NewsfeedMyNews> newsfeedFollowedMeList = newsfeedMyNewsService.newsfeedMyNews(userId);
        if (newsfeedFollowedMeList != null) {
            return ResponseEntity.accepted().body(newsfeedFollowedMeList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드를 조회할 수 없습니다");
        }
    }
}
