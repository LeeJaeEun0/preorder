package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedIFollowDao;
import com.demo.preorder.newsfeed.dto.NewsfeedIFollowDto;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.service.NewsfeedIFollowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsfeedIFollowServiceImpl implements NewsfeedIFollowService {

    private final NewsfeedIFollowDao newsfeedIFollowDao;

    public NewsfeedIFollowServiceImpl(NewsfeedIFollowDao newsfeedIFollowDao) {
        this.newsfeedIFollowDao = newsfeedIFollowDao;
    }

    @Override
    public List<NewsfeedIFollow> newsfeedIFollow(Long userID) {
        return newsfeedIFollowDao.newsfeedIFollow(userID);
    }
}
