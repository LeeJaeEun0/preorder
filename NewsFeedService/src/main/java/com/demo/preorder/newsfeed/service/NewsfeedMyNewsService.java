package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;

import java.util.List;

public interface NewsfeedMyNewsService {
    List<NewsfeedMyNews> newsfeedMyNews(Long userId);
}
