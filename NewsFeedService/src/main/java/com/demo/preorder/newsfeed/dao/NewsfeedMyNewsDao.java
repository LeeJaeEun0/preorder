package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;

import java.util.List;

public interface NewsfeedMyNewsDao {

    NewsfeedMyNews saveNewsfeedMyNews(NewsfeedMyNews newsfeedMyNews);

    List<NewsfeedMyNews> newsfeedMyNews(Long userId);
}
