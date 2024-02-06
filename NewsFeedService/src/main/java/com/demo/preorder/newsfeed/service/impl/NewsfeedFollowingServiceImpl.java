package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowingDao;
import com.demo.preorder.newsfeed.dto.NewsfeedFollowingDto;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;
import com.demo.preorder.newsfeed.service.NewsfeedFollowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsfeedFollowingServiceImpl implements NewsfeedFollowingService {

    private final NewsfeedFollowingDao newsfeedFollowingDao;

    @Override
    public NewsfeedFollowing saveNewsfeedFollowing(NewsfeedFollowingDto newsfeedFollowingDto) {
        NewsfeedFollowing newsfeedFollowing = new NewsfeedFollowing();
        newsfeedFollowing.setUserId(newsfeedFollowingDto.getUserId());
        newsfeedFollowing.setFollowingId(newsfeedFollowingDto.getFollowingId());
        newsfeedFollowing.setType(newsfeedFollowingDto.getType());
        newsfeedFollowing.setTargetId(newsfeedFollowing.getTargetId());
        return newsfeedFollowingDao.saveNewsfeedFollowing(newsfeedFollowing);
    }

    @Override
    public List<NewsfeedFollowing> newsfeedFollowing(Long userID) {
        return newsfeedFollowingDao.newsfeedFollowing(userID);
    }
}
