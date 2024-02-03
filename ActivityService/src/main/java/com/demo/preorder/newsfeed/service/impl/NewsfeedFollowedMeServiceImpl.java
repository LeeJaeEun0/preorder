package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowedMeDao;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.service.NewsfeedFollowedMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsfeedFollowedMeServiceImpl implements NewsfeedFollowedMeService {

    private final NewsfeedFollowedMeDao newsfeedFollowedMeDao;
    
    @Override
    public List<NewsfeedFollowedMe> newsfeedFollowedMe(Long userId) {
        return newsfeedFollowedMeDao.newsfeedFollowedMe(userId);
    }
}
