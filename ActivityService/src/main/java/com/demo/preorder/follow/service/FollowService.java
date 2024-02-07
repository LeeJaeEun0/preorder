package com.demo.preorder.follow.service;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dto.FollowDto;

import java.util.List;

public interface FollowService {
    FollowDto saveFollow(Long userId,FollowDto followDto);

    void deleteFollow(Long userId, FollowDto followDto) throws Exception;

    List<Follow> findFollower (FollowDto followDto);

    List<Follow> findFollowing(Long userId);

}
