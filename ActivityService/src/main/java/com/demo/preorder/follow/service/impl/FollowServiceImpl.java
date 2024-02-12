package com.demo.preorder.follow.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.dto.FollowDto;
import com.demo.preorder.follow.service.FollowService;
import com.demo.preorder.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowDao followDao;
    private final ActivityClient activityClient;

    @Override
    public FollowDto saveFollow(Long userId,FollowDto followDto) {
        Follow follow = new Follow();
        if (followDto.getFollowingId() ==null)
            return null;

        follow.setUserId(activityClient.findUser(userId));
        follow.setFollowingId(activityClient.findUser(followDto.getFollowingId()));
        Follow saved = followDao.insertFollow(follow);

        List<Follow> followList = followDao.findFollowing(saved.getUserId().getId());

        if (followList!= null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("follow");
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
                newsfeedClientDto.setType("follow");
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

        return followDto;
    }

    @Override
    public void deleteFollow(Long userId,FollowDto followDto) throws Exception {
        followDao.deleteFollow(userId, followDto.getFollowingId());
    }

    // 나를 팔로우한 사람
    @Override
    public List<Follow> findFollower(FollowDto followDto) {
        return followDao.findFollower(followDto.getFollowingId());
    }

    // 내가 팔로우한 사람
    @Override
    public List<Follow> findFollowing(Long userId) {
        return followDao.findFollowing(userId);
    }
}
