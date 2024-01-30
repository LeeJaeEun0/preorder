package com.demo.preorder.follow.dao.impl;

import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.repository.FollowRepository;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.repository.NewsfeedIFollowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FollowDaoImpl implements FollowDao {
   private final FollowRepository followRepository;

   private final NewsfeedIFollowRepository newsfeedIFollowRepository;

    public FollowDaoImpl(FollowRepository followRepository, NewsfeedIFollowRepository newsfeedIFollowRepository) {
        this.followRepository = followRepository;
        this.newsfeedIFollowRepository = newsfeedIFollowRepository;
    }

    @Override
    @Transactional
    public Follow insertFollow(Follow follow) {
        Follow saved = followRepository.save(follow);

        Optional<List<Follow>>optionalFollowList = followRepository.findByFollowingIdId(saved.getUserId().getId());

        if (optionalFollowList.isPresent()) {
            List<Follow> followList = optionalFollowList.get();

            for (Follow follows : followList) {
                NewsfeedIFollow newsfeedIFollow = new NewsfeedIFollow();
                newsfeedIFollow.setUserId(follows.getUserId());
                newsfeedIFollow.setFollowingId(follow.getUserId());
                newsfeedIFollow.setType("follow");
                newsfeedIFollow.setTargetId(saved.getId());
                newsfeedIFollowRepository.save(newsfeedIFollow);
            }
        }
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
    public List<Follow> whofollowedMe(Long followingId) {
        Optional<List<Follow>> followme = followRepository.findByFollowingIdId(followingId);
        if(followme.isPresent()){
            List<Follow> followMeList = followme.get();
            return followMeList;
        }
        return null;
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> peopleIfollow(Long userId) {
        Optional<List<Follow>> iFollow = followRepository.findByUserIdId(userId);
        if(iFollow.isPresent()){
            List<Follow> iFollowList = iFollow.get();
            return iFollowList;
        }
        return null;
    }
}
