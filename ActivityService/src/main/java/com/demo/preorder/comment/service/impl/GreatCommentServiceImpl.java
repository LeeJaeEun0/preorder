package com.demo.preorder.comment.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.NewsfeedServiceClient;
import com.demo.preorder.comment.dao.GreatCommentDao;
import com.demo.preorder.comment.dto.GreatCommentResponseDto;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.service.GreatCommentService;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GreatCommentServiceImpl implements GreatCommentService {

    private final GreatCommentDao greatCommentDao;

    private final NewsfeedServiceClient newsfeedServiceClient;

    private final FollowDao followDao;
    @Override
    public GreatCommentResponseDto saveGreatComment(Long userId, Long commentId) {
        GreatComment saved = greatCommentDao.saveGreatComment(userId, commentId);

        List<Follow> followList = followDao.findFollowing(saved.getUserId());

        if (followList!= null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("great_comment");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("GreatCommentServiceImpl - FollowingUserID = {} result = {} Timestamp = {}", follows.getUserId(), result, LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("GreatCommentServiceImpl -  Error saving following for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        List<Follow> followList2 = followDao.findFollower(saved.getUserId());

        if (followList!= null) {

            for (Follow follows : followList2) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getUserId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("great_comment");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("GreatCommentServiceImpl -  FollowerUserID = {} result = {} Timestamp = {}", follows.getUserId(), result, LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("GreatCommentServiceImpl -  Error saving follower for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }
            }
        }

        return new GreatCommentResponseDto(saved);

    }

    @Override
    public void deleteGreatComment(Long userId, Long greatCommentId) {
        greatCommentDao.deleteGreatComment(userId, greatCommentId);
    }

}
