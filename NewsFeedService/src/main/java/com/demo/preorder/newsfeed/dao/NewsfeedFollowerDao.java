package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.NewsfeedFollower;

import java.util.List;

public interface NewsfeedFollowerDao {
    NewsfeedFollower saveNewsfeedFollower(NewsfeedFollower follower);
    List<NewsfeedFollower> newsfeedFollower(Long userId);
}
