package com.demo.preorder.follow.dao;

import com.demo.preorder.follow.entity.Follow;

import java.util.List;

public interface FollowDao {
    Follow insertFollow(Follow follow);

    void deleteFollow(Long userId, Long followingId) throws Exception;

    List<Follow> whofollowedMe(Long followingId);

    List<Follow> peopleIfollow(Long userId);

}
