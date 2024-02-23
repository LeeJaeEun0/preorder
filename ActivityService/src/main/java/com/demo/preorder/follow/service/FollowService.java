package com.demo.preorder.follow.service;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dto.FollowDto;

import java.util.List;

public interface FollowService {
    Follow saveFollow(Long userId,Long followingId);

    void deleteFollow(Long userId, Long followingId);

    List<Follow> findFollower (Long followingId);

    List<Follow> findFollowing(Long userId);

}
