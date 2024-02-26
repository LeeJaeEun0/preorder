package com.demo.preorder.post.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.dto.NewsfeedMyNewsClientDto;
import com.demo.preorder.client.service.NewsfeedServiceClient;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.post.dao.GreatPostDao;
import com.demo.preorder.post.dto.GreatPostResponseDto;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.service.GreatPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class GreatPostServiceImpl implements GreatPostService {

    private final GreatPostDao greatPostDao;

    private final NewsfeedServiceClient newsfeedServiceClient;

    private final FollowDao followDao;

    @Override
    public GreatPostResponseDto saveGreatPost(Long userId, Long postId) {
        GreatPost saved = greatPostDao.saveGreatPost(userId, postId);

        List<Follow> followList = followDao.findFollowing(saved.getUserId());

        if (followList != null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("great_post");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("GreatPostServiceImpl - FollowingUserID = {} result = {} Timestamp = {}", follows.getUserId(), result, LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("GreatPostServiceImpl - Error saving following for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        List<Follow> followList2 = followDao.findFollower(saved.getUserId());

        if (followList != null) {

            for (Follow follows : followList2) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getUserId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("great_post");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("GreatPostServiceImpl -  FollowerUserID = {} result = {} Timestamp = {}", follows.getUserId(), result,LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("GreatPostServiceImpl - Error saving follower for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        NewsfeedMyNewsClientDto newsfeedMyNewsClientDto = new NewsfeedMyNewsClientDto();
        newsfeedMyNewsClientDto.setUserId(saved.getPostId().getUserId());
        newsfeedMyNewsClientDto.setWriterId(saved.getUserId());
        newsfeedMyNewsClientDto.setPostId(saved.getPostId().getId());
        newsfeedMyNewsClientDto.setType("great_post");
        try {
            // 외부 서비스 호출
            ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeedMyNews(newsfeedMyNewsClientDto);
            String result = stringResponseEntity.getBody();
            log.info("GreatPostServiceImpl -  newsfeedMyNewsUserID = {} result = {} Timestamp = {}", newsfeedMyNewsClientDto.getUserId(), result, LocalDateTime.now());
        } catch (Exception e) {
            // 오류 발생 시 처리
            log.error("GreatPostServiceImpl - Error saving follower for userID = {}: {}", newsfeedMyNewsClientDto.getUserId(), e.getMessage(), e);
        }
        return new GreatPostResponseDto(saved);
    }

    @Override
    public void deleteGreatPost(Long userId, Long postId) {
        greatPostDao.deleteGreatPost(userId, postId);
    }

    @Override
    public List<GreatPost> greatPostList(Long postId) {
        return greatPostDao.greatPostList(postId);
    }
}
