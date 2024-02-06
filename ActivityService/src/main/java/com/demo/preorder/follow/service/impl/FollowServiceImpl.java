package com.demo.preorder.follow.service.impl;

import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.dto.FollowDto;
import com.demo.preorder.follow.service.FollowService;
import com.demo.preorder.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowDao followDao;
    private final ActivityClient activityClient;

    @Override
    public FollowDto saveFollow(Long userId,FollowDto followDto) {
        Follow follow = new Follow();
        if (followDto.getFollowingId() ==null)
            return null;

        follow.setUserId(activityClient.findUser(userId));
        follow.setFollowingId(activityClient.findUser(followDto.getFollowingId()));
        followDao.insertFollow(follow);
        return followDto;
    }

    @Override
    public void deleteFollow(Long userId,FollowDto followDto) throws Exception {
        followDao.deleteFollow(userId, followDto.getFollowingId());
    }

    // 나를 팔로우한 사람
    @Override
    public List<Follow> whoFollowedMe(FollowDto followDto) {
        return followDao.whofollowedMe(followDto.getFollowingId());
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> peopleIFollow(Long userId) {
        return followDao.peopleIfollow(userId);
    }
}
