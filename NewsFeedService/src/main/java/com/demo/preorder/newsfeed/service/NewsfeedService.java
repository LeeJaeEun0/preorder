package com.demo.preorder.newsfeed.service;

import com.demo.preorder.newsfeed.dto.NewsfeedDto;
import com.demo.preorder.newsfeed.dto.NewsfeedResponseDto;
import com.demo.preorder.newsfeed.entity.Newsfeed;

import java.util.List;

public interface NewsfeedService {
    NewsfeedResponseDto saveNewsfeed(NewsfeedDto newsfeedDto);

    List<NewsfeedResponseDto> findNewsfeed(Long userId);
}
