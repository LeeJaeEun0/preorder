package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedIFollowDao;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.repository.NewsfeedIFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsfeedIFollowDaoImpl implements NewsfeedIFollowDao {
    private final NewsfeedIFollowRepository newsfeedIFollowRepository;

    @Override
    public List<NewsfeedIFollow> newsfeedIFollow(Long userId) {
        return newsfeedIFollowRepository.findByUserIdIdOrderByCreatedDateDesc(userId);
    }
}
