package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsDto;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;

import java.util.List;

public interface NewsfeedMyNewsService {

    NewsfeedMyNews saveNewsfeedMyNew(NewsfeedMyNewsDto newsfeedMyNewsDto);

    List<NewsfeedMyNews> newsfeedMyNews(Long userId);
}
