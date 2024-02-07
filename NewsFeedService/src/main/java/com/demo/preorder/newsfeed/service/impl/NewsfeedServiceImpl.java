package com.demo.preorder.newsfeed.service.impl;

import com.demo.preorder.newsfeed.dao.NewsfeedDao;
import com.demo.preorder.newsfeed.dto.NewsfeedDto;
import com.demo.preorder.newsfeed.entity.Newsfeed;
import com.demo.preorder.newsfeed.service.NewsfeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class NewsfeedServiceImpl implements NewsfeedService {

    private final NewsfeedDao newsfeedDao;
    @Override
    public Newsfeed saveNewsfeed(NewsfeedDto newsfeedDto) {

        Newsfeed newsfeed = new Newsfeed();
        log.info("info log = {}",newsfeedDto.getUserId());
        log.info("info log = {}",newsfeedDto.getSenderId());
        log.info("info log = {}",newsfeedDto.getType());
        log.info("info log = {}",newsfeedDto.getTargetId());
        newsfeed.setUserId(newsfeedDto.getUserId());
        newsfeed.setSenderId(newsfeedDto.getSenderId());
        newsfeed.setType(newsfeedDto.getType());
        newsfeed.setTargetId(newsfeedDto.getTargetId());
        return newsfeedDao.saveNewsfeed(newsfeed);
    }

    @Override
    public List<Newsfeed> findNewsfeed(Long userId) {
        return newsfeedDao.findNewsfeed(userId);
    }
}
