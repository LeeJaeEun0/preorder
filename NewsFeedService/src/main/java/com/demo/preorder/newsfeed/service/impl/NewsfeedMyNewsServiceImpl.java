package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedMyNewsDao;
import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsDto;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsfeedMyNewsServiceImpl implements NewsfeedMyNewsService {

    private final NewsfeedMyNewsDao newsfeedMyNewsDao;

    @Override
    public NewsfeedMyNews saveNewsfeedMyNew(NewsfeedMyNewsDto newsfeedMyNewsDto) {

        NewsfeedMyNews newsfeedMyNews = new NewsfeedMyNews();
        newsfeedMyNews.setUserId(newsfeedMyNewsDto.getUserId());
        newsfeedMyNews.setWriterId(newsfeedMyNewsDto.getWriterId());
        newsfeedMyNews.setType(newsfeedMyNewsDto.getType());
        newsfeedMyNews.setPostId(newsfeedMyNewsDto.getPostId());
        return newsfeedMyNewsDao.saveNewsfeedMyNews(newsfeedMyNews);
    }

    @Override
    public List<NewsfeedMyNews> newsfeedMyNews(Long userId) {
        return newsfeedMyNewsDao.newsfeedMyNews(userId);
    }
}
