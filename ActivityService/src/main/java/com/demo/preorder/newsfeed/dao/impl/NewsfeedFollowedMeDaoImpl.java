package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedFollowedMeDao;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.repository.NewsfeedFollowedMeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsfeedFollowedMeDaoImpl implements NewsfeedFollowedMeDao {

    private final NewsfeedFollowedMeRepository newsfeedFollowedMeRepository;

    @Override
    public List<NewsfeedFollowedMe> newsfeedFollowedMe(Long userId) {
        return newsfeedFollowedMeRepository.findByUserIdIdOrderByCreatedDateDesc(userId);
    }
}
