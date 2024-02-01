package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowedMeDao;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.service.NewsfeedFollowedMeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsfeedFollowedMeServiceImpl implements NewsfeedFollowedMeService {
    private final NewsfeedFollowedMeDao newsfeedFollowedMeDao;


    public NewsfeedFollowedMeServiceImpl(NewsfeedFollowedMeDao newsfeedFollowedMeDao) {
        this.newsfeedFollowedMeDao = newsfeedFollowedMeDao;
    }

    @Override
    public List<NewsfeedFollowedMe> newsfeedFollowedMe(Long userId) {
        return newsfeedFollowedMeDao.newsfeedFollowedMe(userId);
    }
}
