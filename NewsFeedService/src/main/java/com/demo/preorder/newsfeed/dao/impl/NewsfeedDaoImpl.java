package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedDao;
import com.demo.preorder.newsfeed.entity.Newsfeed;
import com.demo.preorder.newsfeed.repository.NewsfeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsfeedDaoImpl implements NewsfeedDao {

    private final NewsfeedRepository newsfeedRepository;

    @Override
    public Newsfeed saveNewsfeed(Newsfeed newsfeed) {
        return newsfeedRepository.save(newsfeed);
    }

    @Override
    public List<Newsfeed> findNewsfeed(Long userId) {
        return newsfeedRepository.findByUserIdOrderByCreatedDateDesc(userId);
    }
}
