package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.entity.NewsfeedFollower;

import java.util.List;

public interface NewsfeedFollowerService {
    List<NewsfeedFollower> newsfeedFollower(Long userId);
}
