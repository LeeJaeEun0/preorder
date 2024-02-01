package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedMyNewsDao;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.repository.NewsfeedMyNewsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsfeedMyNewsDaoImpl implements NewsfeedMyNewsDao {

    private final NewsfeedMyNewsRepository newsfeedMyNewsRepository;

    public NewsfeedMyNewsDaoImpl(NewsfeedMyNewsRepository newsfeedMyNewsRepository) {
        this.newsfeedMyNewsRepository = newsfeedMyNewsRepository;
    }

    @Override
    public List<NewsfeedMyNews> newsfeedMyNews(Long userId) {
        return newsfeedMyNewsRepository.findByUserIdIdOrderByCreatedDateDesc(userId);
    }
}
