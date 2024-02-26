package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedDao;
import com.demo.preorder.newsfeed.dto.NewsfeedDto;
import com.demo.preorder.newsfeed.dto.NewsfeedResponseDto;
import com.demo.preorder.newsfeed.entity.Newsfeed;
import com.demo.preorder.newsfeed.service.NewsfeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        // newsfeedDao에서 Newsfeed 객체의 리스트를 가져옴
        List<Newsfeed> newsfeeds = newsfeedDao.findNewsfeed(userId);

        // Newsfeed 객체의 리스트를 NewsfeedResponseDto 객체의 리스트로 변환
        List<NewsfeedResponseDto> newsfeedResponseDtos = newsfeeds.stream()
                .map(NewsfeedResponseDto::new) // Newsfeed 객체를 NewsfeedResponseDto로 변환하는 생성자 참조
                .collect(Collectors.toList());

        return newsfeedResponseDtos;
    }
}
