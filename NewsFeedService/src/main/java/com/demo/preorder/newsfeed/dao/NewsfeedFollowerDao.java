package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.NewsfeedFollower;

import java.util.List;

public interface NewsfeedFollowerDao {
    List<NewsfeedFollower> newsfeedFollower(Long userId);
}
