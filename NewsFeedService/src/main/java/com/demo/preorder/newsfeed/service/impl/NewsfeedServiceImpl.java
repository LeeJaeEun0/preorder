package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedDao;
import com.demo.preorder.newsfeed.dto.NewsfeedDto;
import com.demo.preorder.newsfeed.dto.NewsfeedResponseDto;
import com.demo.preorder.newsfeed.entity.Newsfeed;
import com.demo.preorder.newsfeed.service.NewsfeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsfeedServiceImpl implements NewsfeedService {

    private final NewsfeedDao newsfeedDao;

    @Override
    public NewsfeedResponseDto saveNewsfeed(NewsfeedDto newsfeedDto) {

        Newsfeed newsfeed = new Newsfeed();
        newsfeed.setUserId(newsfeedDto.getUserId());
        newsfeed.setSenderId(newsfeedDto.getSenderId());
        newsfeed.setType(newsfeedDto.getType());
        newsfeed.setTargetId(newsfeedDto.getTargetId());
        return new NewsfeedResponseDto(newsfeedDao.saveNewsfeed(newsfeed));
    }

    @Override
    public List<NewsfeedResponseDto> findNewsfeed(Long userId) {
        List<Newsfeed> newsfeeds = newsfeedDao.findNewsfeed(userId);

        List<NewsfeedResponseDto> newsfeedResponseDtos = newsfeeds.stream()
                .map(NewsfeedResponseDto::new)
                .collect(Collectors.toList());

        return newsfeedResponseDtos;
    }
}
