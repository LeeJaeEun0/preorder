package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.dto.NewsfeedFollowerDto;
import com.demo.preorder.newsfeed.entity.NewsfeedFollower;

import java.util.List;

public interface NewsfeedFollowerService {

    NewsfeedFollower saveNewsfeedFollower(NewsfeedFollowerDto newsfeedFollowerDto);
    List<NewsfeedFollower> newsfeedFollower(Long userId);
}
