package com.demo.preorder.follow.dao.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.repository.FollowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FollowDaoImpl implements FollowDao {
    private final FollowRepository followRepository;

    @Override
    @Transactional
    public Follow insertFollow(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public void deleteFollow(Long userId, Long followingId) {
        Optional<Follow> deleteFollow = followRepository.findFollow(userId, followingId);
        if (deleteFollow.isPresent()) {
            Follow follow = deleteFollow.get();
            followRepository.delete(follow);
        } else {
            throw new CustomException(ErrorCode.NOT_EXISTS_FOLLOW);
        }
    }

    // 나를 팔로우한 사람
    @Override
    public List<Follow> findFollower(Long followingId) {
        Optional<List<Follow>> follower = followRepository.findByFollowingId(followingId);
        return follower.orElse(null);
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> findFollowing(Long userId) {
        Optional<List<Follow>> following = followRepository.findByUserId(userId);
        return following.orElse(null);
    }
}
