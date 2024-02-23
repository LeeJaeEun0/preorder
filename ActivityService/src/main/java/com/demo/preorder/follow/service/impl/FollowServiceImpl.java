package com.demo.preorder.follow.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.NewsfeedServiceClient;
import com.demo.preorder.follow.dto.FollowDto;
import com.demo.preorder.follow.dto.FollowResponseDto;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowDao followDao;

    private final NewsfeedServiceClient newsfeedServiceClient;

    @Override
    public FollowResponseDto saveFollow(Long userId, Long followingId) {
        Follow follow = new Follow();
        if (followingId ==null)
            return null;

        follow.setUserId(userId);
        follow.setFollowingId(followingId);
        Follow saved = followDao.insertFollow(follow);

        List<Follow> followList = followDao.findFollowing(saved.getUserId());

        if (followList!= null) {

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
                    log.info("Info log: Following - userID={} result={}", follows.getUserId(), result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving following for userID={}: {}", follows.getUserId(), e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }
            }
        }

        List<Follow> followList2 = followDao.findFollower(saved.getUserId());

        if (followList!= null) {

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
                    log.info("Info log: Follower - userID={} result={}", follows.getUserId(), result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving follower for userID={}: {}", follows.getUserId(), e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }
            }
        }

        return new FollowResponseDto(saved);
    }

    @Override
    public void deleteFollow(Long userId,Long followingId){
        followDao.deleteFollow(userId, followingId);
    }

    // 나를 팔로우한 사람
    @Override
    public List<Follow> findFollower(Long followingId) {
        return followDao.findFollower(followingId);
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> findFollowing(Long userId) {
        return followDao.findFollowing(userId);
    }
}
