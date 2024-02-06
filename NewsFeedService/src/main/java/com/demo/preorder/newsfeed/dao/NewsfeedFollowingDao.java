package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.NewsfeedFollower;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;

import java.util.List;

public interface NewsfeedFollowingDao {
    NewsfeedFollowing saveNewsfeedFollowing(NewsfeedFollowing newsfeedFollowing);
    List<NewsfeedFollowing> newsfeedFollowing(Long userId);
}
