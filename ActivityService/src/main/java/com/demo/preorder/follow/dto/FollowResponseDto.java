package com.demo.preorder.follow.dto;

import com.demo.preorder.follow.entity.Follow;
import lombok.Getter;

@Getter
public class FollowResponseDto {
    private Long userId;
    private Long followingId;

    public FollowResponseDto(Follow follow){
        this.userId = follow.getUserId();
        this.followingId = follow.getFollowingId();
    }
}
