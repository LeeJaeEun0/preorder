package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedMyNewsDao;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsfeedMyNewsServiceImpl implements NewsfeedMyNewsService {

    private final NewsfeedMyNewsDao newsfeedMyNewsDao;

    public NewsfeedMyNewsServiceImpl(NewsfeedMyNewsDao newsfeedMyNewsDao) {
        this.newsfeedMyNewsDao = newsfeedMyNewsDao;
    }

    @Override
    public List<NewsfeedMyNews> newsfeedMyNews(Long userId) {
        return newsfeedMyNewsDao.newsfeedMyNews(userId);
    }
}
