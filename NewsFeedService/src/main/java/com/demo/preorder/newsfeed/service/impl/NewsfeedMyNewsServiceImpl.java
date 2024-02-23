package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedMyNewsDao;
import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsDto;
import com.demo.preorder.newsfeed.dto.NewsfeedMyNewsResponseDto;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.service.NewsfeedMyNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsfeedMyNewsServiceImpl implements NewsfeedMyNewsService {

    private final NewsfeedMyNewsDao newsfeedMyNewsDao;

    @Override
    public NewsfeedMyNewsResponseDto saveNewsfeedMyNew(NewsfeedMyNewsDto newsfeedMyNewsDto) {

        NewsfeedMyNews newsfeedMyNews = new NewsfeedMyNews();
        newsfeedMyNews.setUserId(newsfeedMyNewsDto.getUserId());
        newsfeedMyNews.setWriterId(newsfeedMyNewsDto.getWriterId());
        newsfeedMyNews.setType(newsfeedMyNewsDto.getType());
        newsfeedMyNews.setPostId(newsfeedMyNewsDto.getPostId());
        return new NewsfeedMyNewsResponseDto(newsfeedMyNewsDao.saveNewsfeedMyNews(newsfeedMyNews));
    }

    @Override
    public List<NewsfeedMyNewsResponseDto> newsfeedMyNews(Long userId) {
        // newsfeedMyNewsDao에서 NewsfeedMyNews 객체의 리스트를 가져옴
        List<NewsfeedMyNews> newsfeedMyNewsList = newsfeedMyNewsDao.newsfeedMyNews(userId);

        // NewsfeedMyNews 객체의 리스트를 NewsfeedMyNewsResponseDto 객체의 리스트로 변환
        List<NewsfeedMyNewsResponseDto> newsfeedMyNewsResponseDtos = newsfeedMyNewsList.stream()
                .map(NewsfeedMyNewsResponseDto::new) // NewsfeedMyNews 객체를 NewsfeedMyNewsResponseDto로 변환하는 생성자 참조
                .collect(Collectors.toList());

        return newsfeedMyNewsResponseDtos;
    }
}
