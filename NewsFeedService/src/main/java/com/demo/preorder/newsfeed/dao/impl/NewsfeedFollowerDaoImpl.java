package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowerDao;
import com.demo.preorder.newsfeed.entity.NewsfeedFollower;
import com.demo.preorder.newsfeed.repository.NewsfeedFollowedMeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsfeedFollowerDaoImpl implements NewsfeedFollowerDao {

    private final NewsfeedFollowedMeRepository newsfeedFollowedMeRepository;

    @Override
    public NewsfeedFollower saveNewsfeedFollower(NewsfeedFollower newsfeedFollower) {
        return newsfeedFollowedMeRepository.save(newsfeedFollower);
    }

    @Override
    public List<NewsfeedFollower> newsfeedFollower(Long userId) {
        return newsfeedFollowedMeRepository.findByUserIdIdOrderByCreatedDateDesc(userId);
    }
}
