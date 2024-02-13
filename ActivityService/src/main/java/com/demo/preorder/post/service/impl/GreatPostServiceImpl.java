package com.demo.preorder.post.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.dto.NewsfeedMyNewsClientDto;
import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.post.dao.GreatPostDao;
import com.demo.preorder.post.dto.GreatPostDto;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.service.GreatPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class GreatPostServiceImpl implements GreatPostService {

    private final GreatPostDao greatPostDao;
    private final ActivityClient activityClient;

    private final FollowDao followDao;
    @Override
    public GreatPost saveGreatPost(Long userId, GreatPostDto greatPostDto) {
        GreatPost saved = greatPostDao.saveGreatPost(userId, greatPostDto.getPostId());

        List<Follow> followList = followDao.findFollowing(saved.getUserId().getId());

        if (followList!= null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("great_post");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    String result = activityClient.saveNewsfeed(newsfeedClientDto);
                    log.info("Info log: Following - userID={} result={}", follows.getUserId(), result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving following for userID={}: {}", follows.getUserId(), e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }
            }
        }

        List<Follow> followList2 = followDao.findFollower(saved.getUserId().getId());

        if (followList!= null) {

            for (Follow follows : followList2) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getUserId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("great_post");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    String result = activityClient.saveNewsfeed(newsfeedClientDto);
                    log.info("Info log: Follower - userID={} result={}", follows.getUserId(), result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving follower for userID={}: {}", follows.getUserId(), e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
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
            String result = activityClient.saveNewsfeedMyNews(newsfeedMyNewsClientDto);
            log.info("Info log: newsfeedMyNews - userID={} result={}", newsfeedMyNewsClientDto.getUserId(), result);
        } catch (Exception e) {
            // 오류 발생 시 처리
            log.error("Error saving follower for userID={}: {}", newsfeedMyNewsClientDto.getUserId(), e.getMessage(), e);
            // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
        }
        return saved;
    }

    @Override
    public void deleteGreatPost(Long userId, GreatPostDto greatPostDto) {
        greatPostDao.deleteGreatPost(userId, greatPostDto.getGreatPostId());
    }

    @Override
    public List<GreatPost> greatPostList(GreatPostDto greatPostDto) {
        return greatPostDao.greatPostList(greatPostDto.getPostId());
    }
}
