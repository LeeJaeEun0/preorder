package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.dto.NewsfeedDto;
import com.demo.preorder.newsfeed.entity.Newsfeed;

import java.util.List;

public interface NewsfeedService {
    Newsfeed saveNewsfeed(NewsfeedDto newsfeedDto);

    List<Newsfeed> findNewsfeed(Long userId);
}
