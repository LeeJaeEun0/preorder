package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedIFollowDao;
import com.demo.preorder.newsfeed.dto.NewsfeedIFollowDto;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.service.NewsfeedIFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsfeedIFollowServiceImpl implements NewsfeedIFollowService {

    private final NewsfeedIFollowDao newsfeedIFollowDao;

    @Override
    public List<NewsfeedIFollow> newsfeedIFollow(Long userID) {
        return newsfeedIFollowDao.newsfeedIFollow(userID);
    }
}
