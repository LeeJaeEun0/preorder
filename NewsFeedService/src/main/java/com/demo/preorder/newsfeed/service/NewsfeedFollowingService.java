package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.dto.NewsfeedFollowingDto;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;

import java.util.List;

public interface NewsfeedFollowingService {
    NewsfeedFollowing saveNewsfeedFollowing(NewsfeedFollowingDto newsfeedFollowingDto);
    List<NewsfeedFollowing> newsfeedFollowing(Long userId);
}
