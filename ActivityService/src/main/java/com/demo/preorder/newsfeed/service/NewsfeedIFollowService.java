package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.dto.NewsfeedIFollowDto;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;

import java.util.List;

public interface NewsfeedIFollowService {
    List<NewsfeedIFollow> newsfeedIFollow(Long userId);
}
