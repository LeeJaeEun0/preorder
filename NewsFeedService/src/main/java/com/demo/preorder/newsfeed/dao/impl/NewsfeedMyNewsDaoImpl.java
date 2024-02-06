package com.demo.preorder.newsfeed.dao.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedMyNewsDao;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.repository.NewsfeedMyNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsfeedMyNewsDaoImpl implements NewsfeedMyNewsDao {

    private final NewsfeedMyNewsRepository newsfeedMyNewsRepository;

    @Override
    public NewsfeedMyNews saveNewsfeedMyNews(NewsfeedMyNews newsfeedMyNews) {
        return newsfeedMyNewsRepository.save(newsfeedMyNews);
    }

    @Override
    public List<NewsfeedMyNews> newsfeedMyNews(Long userId) {
        return newsfeedMyNewsRepository.findByUserIdIdOrderByCreatedDateDesc(userId);
    }
}
