package com.demo.preorder.client.service;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.dto.NewsfeedMyNewsClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@ComponentScan
@FeignClient(name = "NewsFeedService", url = "${newsfeed.service.url}")
public interface NewsfeedServiceClient {
    @PostMapping("/api/internal/newsfeeds")
    ResponseEntity<String> saveNewsfeed(NewsfeedClientDto newsfeedClientDto);

    @PostMapping("/api/internal/newsfeeds/myNews")
    ResponseEntity<String> saveNewsfeedMyNews(NewsfeedMyNewsClientDto newsfeedMyNewsClientDto);
}
