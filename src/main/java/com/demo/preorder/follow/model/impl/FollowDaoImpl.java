package com.demo.preorder.follow.model.impl;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.model.FollowDao;
import com.demo.preorder.follow.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FollowDaoImpl implements FollowDao {
   private final FollowRepository followRepository;

    public FollowDaoImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
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
    public List<Follow> whofollowedMe(Long followingId) {
        Optional<List<Follow>> followme = followRepository.whofollowedMe(followingId);
        if(followme.isPresent()){
            List<Follow> followMeList = followme.get();
            return followMeList;
        }
        return null;
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> peopleIfollow(Long userId) {
        Optional<List<Follow>> iFollow = followRepository.peopleIfollow(userId);
        if(iFollow.isPresent()){
            List<Follow> iFollowList = iFollow.get();
            return iFollowList;
        }
        return null;
    }
}
