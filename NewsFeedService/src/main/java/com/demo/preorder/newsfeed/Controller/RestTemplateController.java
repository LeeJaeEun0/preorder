package com.demo.preorder.newsfeed.Controller;

import com.demo.preorder.newsfeed.dto.NewsfeedFollowerDto;
import com.demo.preorder.newsfeed.dto.NewsfeedFollowingDto;
import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsDto;
import com.demo.preorder.newsfeed.entity.NewsfeedFollower;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedFollowerService;
import com.demo.preorder.newsfeed.service.NewsfeedFollowingService;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/newsfeeds")
@RequiredArgsConstructor
public class RestTemplateController {

    private NewsfeedFollowerService newsfeedFollowerService;

    private NewsfeedFollowingService newsfeedFollowingService;

    private NewsfeedMyNewsService newsfeedMyNewsService;

    @PostMapping("/following")
    public ResponseEntity<?> createNewsfeedFollowing(@RequestBody NewsfeedFollowingDto newsfeedFollowingDto){
        NewsfeedFollowing newsfeedFollowing = newsfeedFollowingService.saveNewsfeedFollowing(newsfeedFollowingDto);
        if(newsfeedFollowing != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(newsfeedFollowing);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드 작성에 실패했습니다.");
        }
    }

    @PostMapping("/follower")
    public ResponseEntity<?> createNewsfeedFollower(@RequestBody NewsfeedFollowerDto newsfeedFollowerDto){
        NewsfeedFollower newsfeedFollower = newsfeedFollowerService.saveNewsfeedFollower(newsfeedFollowerDto);
        if(newsfeedFollower != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(newsfeedFollower);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드 작성에 실패했습니다.");
        }
    }

    @PostMapping("/myNews")
    public ResponseEntity<?> createNewsfeedMyNews(@RequestBody NewsfeedMyNewsDto newsfeedMyNewsDto){
        NewsfeedMyNews newsfeedMyNews = newsfeedMyNewsService.saveNewsfeedMyNew(newsfeedMyNewsDto);
        if(newsfeedMyNews != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(newsfeedMyNews);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("뉴스피드 작성에 실패했습니다.");
        }
    }
}
