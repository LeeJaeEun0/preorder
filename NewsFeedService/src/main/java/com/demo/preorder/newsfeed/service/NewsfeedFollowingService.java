package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;

import java.util.List;

public interface NewsfeedFollowingService {
    List<NewsfeedFollowing> newsfeedFollowing(Long userId);
}
