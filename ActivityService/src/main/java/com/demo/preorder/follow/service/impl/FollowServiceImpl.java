package com.demo.preorder.follow.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.NewsfeedServiceClient;
import com.demo.preorder.follow.dto.FollowResponseDto;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowDao followDao;

    private final NewsfeedServiceClient newsfeedServiceClient;

    @Override
    public FollowResponseDto saveFollow(Long userId, Long followingId) {
        Follow follow = new Follow();
        if (followingId == null)
            return null;

        follow.setUserId(userId);
        follow.setFollowingId(followingId);
        Follow saved = followDao.insertFollow(follow);

        List<Follow> followList = followDao.findFollowing(saved.getUserId());

        if (followList != null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("follow");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("FollowServiceImpl - FollowingUserID = {} result = {} Timestamp = {}", follows.getUserId(), result, LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("FollowServiceImpl - Error saving following for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        List<Follow> followList2 = followDao.findFollower(saved.getUserId());

        if (followList != null) {

            for (Follow follows : followList2) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getUserId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("follow");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("FollowServiceImpl - FollowerUserID = {} result = {} Timestamp = {}", follows.getUserId(), result,LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("FollowServiceImpl - Error saving follower for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        return new FollowResponseDto(saved);
    }

    @Override
    public void deleteFollow(Long userId, Long followingId) {
        followDao.deleteFollow(userId, followingId);
    }

    // 나를 팔로우한 사람
    @Override
    public List<FollowResponseDto> findFollower(Long followingId) {
        List<Follow> followers = followDao.findFollower(followingId);

        List<FollowResponseDto> followResponseDtos = followers.stream()
                .map(FollowResponseDto::new)
                .collect(Collectors.toList());

        return followResponseDtos;
    }

    // 내가 팔로우한 사람
    @Override
    public List<FollowResponseDto> findFollowing(Long userId) {

        List<Follow> followers = followDao.findFollowing(userId);
        List<FollowResponseDto> followResponseDtos = followers.stream()
                .map(FollowResponseDto::new) // Follow 객체를 FollowResponseDto로 변환하는 생성자 참조
                .collect(Collectors.toList());

        return followResponseDtos;
    }
}
