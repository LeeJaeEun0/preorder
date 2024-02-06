package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowingDao;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;
import com.demo.preorder.newsfeed.repository.NewsfeedIFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsfeedFollowingDaoImpl implements NewsfeedFollowingDao {
    private final NewsfeedIFollowRepository newsfeedIFollowRepository;

    @Override
    public NewsfeedFollowing saveNewsfeedFollowing(NewsfeedFollowing newsfeedFollowing) {
        return newsfeedIFollowRepository.save(newsfeedFollowing);
    }

    @Override
    public List<NewsfeedFollowing> newsfeedFollowing(Long userId) {
        return newsfeedIFollowRepository.findByUserIdIdOrderByCreatedDateDesc(userId);
    }
}
