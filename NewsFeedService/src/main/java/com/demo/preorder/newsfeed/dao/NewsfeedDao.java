package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.Newsfeed;

import java.util.List;

public interface NewsfeedDao {
    Newsfeed saveNewsfeed(Newsfeed newsfeed);
    List<Newsfeed> findNewsfeed(Long userId);
}
