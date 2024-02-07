package com.demo.preorder.comment.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.comment.dao.GreatCommentDao;
import com.demo.preorder.comment.dto.GreatCommentDto;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.service.GreatCommentService;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GreatCommentServiceImpl implements GreatCommentService {

    private final GreatCommentDao greatCommentDao;

    private final ActivityClient activityClient;

    private final FollowDao followDao;
    @Override
    public GreatComment saveGreatComment(Long userId, GreatCommentDto greatCommentDto) {
        GreatComment saved = greatCommentDao.saveGreatComment(userId, greatCommentDto.getCommentId());
        // 내가 팔로우한 사람 찾아서 newsfeed
        List<Follow> followList = followDao.findFollowing(saved.getUserId().getId());

        if (followList!= null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("great_comment");
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
        return saved;

    }

    @Override
    public void deleteGreatComment(Long userId, GreatCommentDto greatCommentDto) {
        greatCommentDao.deleteGreatComment(userId, greatCommentDto.getGreatCommentId());
    }

    @Override
    public List<GreatComment> greatCommentList(GreatCommentDto greatCommentDto) {
        return greatCommentDao.greatCommentList(greatCommentDto.getCommentId());
    }
}
