package com.demo.preorder.follow.service;

import com.demo.preorder.follow.dto.FollowResponseDto;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dto.FollowDto;

import java.util.List;

public interface FollowService {
    FollowResponseDto saveFollow(Long userId, Long followingId);

    void deleteFollow(Long userId, Long followingId);

    List<FollowResponseDto> findFollower (Long followingId);

    List<FollowResponseDto> findFollowing(Long userId);

}
