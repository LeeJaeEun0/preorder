package com.demo.preorder.newsfeed.dao;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;

import java.util.List;

public interface NewsfeedFollowedMeDao {
    List<NewsfeedFollowedMe> newsfeedFollowedMe(Long userId);
}
