package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;

import java.util.List;

public interface NewsfeedFollowingDao {
    List<NewsfeedFollowing> newsfeedFollowing(Long userId);
}
