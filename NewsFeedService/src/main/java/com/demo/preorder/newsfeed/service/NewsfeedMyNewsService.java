package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsDto;
import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsResponseDto;

import java.util.List;

public interface NewsfeedMyNewsService {

    NewsfeedMyNewsResponseDto saveNewsfeedMyNew(NewsfeedMyNewsDto newsfeedMyNewsDto);

    List<NewsfeedMyNewsResponseDto> newsfeedMyNews(Long userId);
}
