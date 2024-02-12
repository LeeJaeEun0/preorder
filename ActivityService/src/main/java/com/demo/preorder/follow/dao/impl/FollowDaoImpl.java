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
        Follow saved = followRepository.save(follow);

        Optional<List<Follow>>optionalFollowList = followRepository.findByFollowingIdId(saved.getUserId().getId());

//        if (optionalFollowList.isPresent()) {
//            List<Follow> followList = optionalFollowList.get();
//
//            for (Follow follows : followList) {
//                NewsfeedIFollow newsfeedIFollow = new NewsfeedIFollow();
//                newsfeedIFollow.setUserId(follows.getUserId());
//                newsfeedIFollow.setFollowingId(follow.getUserId());
//                newsfeedIFollow.setType("follow");
//                newsfeedIFollow.setTargetId(saved.getId());
//                newsfeedIFollowRepository.save(newsfeedIFollow);
//            }
//        }
        return saved;
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
