package com.demo.preorder.follow.service.impl;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.model.FollowDao;
import com.demo.preorder.follow.model.FollowDto;
import com.demo.preorder.follow.service.FollowService;
import com.demo.preorder.member.model.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FollowServiceImpl implements FollowService {

    private final FollowDao followDao;

    private final UserDao userDao;
    public FollowServiceImpl(FollowDao followDao, UserDao userDao) {
        this.followDao = followDao;
        this.userDao = userDao;
    }

    @Override
    public FollowDto saveFollow(FollowDto followDto) {
        Follow follow = new Follow();
        if (followDto.getUserId() ==null)
            return null;
        if (followDto.getFollowingId() ==null)
            return null;

        follow.setUserId(userDao.findUser(followDto.getUserId()));
        follow.setFollowingId(userDao.findUser(followDto.getFollowingId()));
        followDao.insertFollow(follow);
        return followDto;
    }

    @Override
    public void deleteFollow(FollowDto followDto) throws Exception {
        followDao.deleteFollow(followDto.getUserId(), followDto.getFollowingId());
    }

    // 나를 팔로우한 사람
    @Override
    public List<Follow> whoFollowedMe(FollowDto followDto) {
        return followDao.whofollowedMe(followDto.getFollowingId());
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> peopleIFollow(FollowDto followDto) {
        return followDao.peopleIfollow(followDto.getUserId());
    }
}
