package com.demo.preorder.follow.dao.impl;

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
    public void deleteFollow(Long userId, Long followingId) throws Exception{
        Optional<Follow> deleteFollow = followRepository.findFollow(userId, followingId);
        if(deleteFollow.isPresent()){
            Follow follow = deleteFollow.get();
            followRepository.delete(follow);
        }else{
            throw new Exception();
        }
    }

    // 나를 팔로우한 사람
    @Override
    public List<Follow> findFollower(Long followingId) {
        Optional<List<Follow>> follower = followRepository.findByFollowingIdId(followingId);
        if(follower.isPresent()){
            List<Follow> followerList = follower.get();
            return followerList ;
        }
        return null;
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> findFollowing(Long userId) {
        Optional<List<Follow>> following = followRepository.findByUserIdId(userId);
        if(following .isPresent()){
            List<Follow> followingList = following.get();
            return followingList;
        }
        return null;
    }
}
