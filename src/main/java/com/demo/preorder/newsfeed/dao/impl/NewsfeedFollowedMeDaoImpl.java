package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowedMeDao;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.repository.NewsfeedFollowedMeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsfeedFollowedMeDaoImpl implements NewsfeedFollowedMeDao {

    private final NewsfeedFollowedMeRepository newsfeedFollowedMeRepository;

    public NewsfeedFollowedMeDaoImpl(NewsfeedFollowedMeRepository newsfeedFollowedMeRepository) {
        this.newsfeedFollowedMeRepository = newsfeedFollowedMeRepository;
    }

    @Override
    public List<NewsfeedFollowedMe> newsfeedFollowedMe(Long userId) {
        return newsfeedFollowedMeRepository.findByUserIdIdOrderByCreatedDateDesc(userId);
    }
}
