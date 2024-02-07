package com.demo.preorder.newsfeed.Controller;

import com.demo.preorder.newsfeed.dto.NewsfeedDto;
import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsDto;
import com.demo.preorder.newsfeed.entity.Newsfeed;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import com.demo.preorder.newsfeed.service.NewsfeedService;
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

    private final NewsfeedService newsfeedService;

    private final NewsfeedMyNewsService newsfeedMyNewsService;

    @PostMapping
    public ResponseEntity<?> createNewsfeed(@RequestBody NewsfeedDto newsfeedDto){
        Newsfeed newsfeed = newsfeedService.saveNewsfeed(newsfeedDto);
        if(newsfeed != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(newsfeed);
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
