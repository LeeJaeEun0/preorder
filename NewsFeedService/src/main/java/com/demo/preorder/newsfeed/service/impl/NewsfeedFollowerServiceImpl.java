package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowerDao;
import com.demo.preorder.newsfeed.dto.NewsfeedFollowerDto;
import com.demo.preorder.newsfeed.entity.NewsfeedFollower;
import com.demo.preorder.newsfeed.service.NewsfeedFollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsfeedFollowerServiceImpl implements NewsfeedFollowerService {

    private final NewsfeedFollowerDao newsfeedFollowerDao;

    @Override
    public NewsfeedFollower saveNewsfeedFollower(NewsfeedFollowerDto newsfeedFollowerDto) {
        NewsfeedFollower newsfeedFollower = new NewsfeedFollower();
        newsfeedFollower.setUserId(newsfeedFollowerDto.getUserId());
        newsfeedFollower.setFollowerId(newsfeedFollowerDto.getFollowerId());
        newsfeedFollower.setTargetId(newsfeedFollower.getTargetId());
        return newsfeedFollowerDao.saveNewsfeedFollower(newsfeedFollower);
    }

    @Override
    public List<NewsfeedFollower> newsfeedFollower(Long userId) {
        return newsfeedFollowerDao.newsfeedFollower(userId);
    }
}
