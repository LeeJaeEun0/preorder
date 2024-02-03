package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;

import java.util.List;

public interface NewsfeedIFollowDao {
    List<NewsfeedIFollow> newsfeedIFollow(Long userId);
}
