package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;

import java.util.List;

public interface NewsfeedFollowedMeService {
    List<NewsfeedFollowedMe> newsfeedFollowedMe(Long userId);
}
