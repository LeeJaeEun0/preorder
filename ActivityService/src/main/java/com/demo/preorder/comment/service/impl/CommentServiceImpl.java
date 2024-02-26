package com.demo.preorder.comment.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.dto.NewsfeedMyNewsClientDto;
import com.demo.preorder.client.service.NewsfeedServiceClient;
import com.demo.preorder.comment.dao.CommentDao;
import com.demo.preorder.comment.dto.CommentDto;
import com.demo.preorder.comment.dto.CommentReplayDto;
import com.demo.preorder.comment.dto.CommentResponseDto;
import com.demo.preorder.comment.dto.CommentUpdateDto;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.service.CommentService;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.entity.Post;
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
public class CommentServiceImpl implements CommentService {
   private final PostDao postDao;

   private final NewsfeedServiceClient newsfeedServiceClient;

   private final CommentDao commentDao;

    private final FollowDao followDao;

    @Override
    public CommentResponseDto saveComment(Long userId, CommentDto commentDto) {
        Comment comment = new Comment();
        Post post = postDao.selectPost(commentDto.getPostId());
        if(post == null) throw new CustomException(ErrorCode.INVALID_POST);
        comment.setPostId(post);
        comment.setUseId(userId);
        comment.setContent(commentDto.getContent());
        comment.setCommentDepth(0);
        int groupId = commentDao.findGroupId();
        comment.setCommentGroup(groupId+1);

        Comment saved = commentDao.saveComment(comment);

        List<Follow> followList = followDao.findFollowing(saved.getUseId());

        if (followList!= null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getPostId().getUserId());
                newsfeedClientDto.setType("comment");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("CommentServiceImpl - followingUserID= {} result = {} Timestamp = {}", follows.getUserId(), result, LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("CommentServiceImpl - Error saving following for userID= {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        List<Follow> followList2 = followDao.findFollower(saved.getUseId());

        if (followList!= null) {

            for (Follow follows : followList2) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getUserId());
                newsfeedClientDto.setSenderId(saved.getPostId().getUserId());
                newsfeedClientDto.setType("comment");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("CommentServiceImpl - FollowerUserID = {} result = {} Timestamp = {}", follows.getUserId(), result,LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("CommentServiceImpl - Error saving follower for userID={}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        NewsfeedMyNewsClientDto newsfeedMyNewsClientDto = new NewsfeedMyNewsClientDto();
        newsfeedMyNewsClientDto.setUserId(saved.getPostId().getUserId());
        newsfeedMyNewsClientDto.setWriterId(saved.getUseId());
        newsfeedMyNewsClientDto.setPostId(saved.getPostId().getId());
        newsfeedMyNewsClientDto.setType("comment");
        try {
            // 외부 서비스 호출
            ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeedMyNews(newsfeedMyNewsClientDto);
            String result = stringResponseEntity.getBody();
            log.info("CommentServiceImpl - newsfeedMyNews - userID = {} result = {} Timestamp = {}", newsfeedMyNewsClientDto.getUserId(), result, LocalDateTime.now());
        } catch (Exception e) {
            // 오류 발생 시 처리
            log.error("CommentServiceImpl - Error saving follower for userID={}: {}", newsfeedMyNewsClientDto.getUserId(), e.getMessage(), e);
        }



        return new CommentResponseDto(saved);
    }

    @Override
    public CommentResponseDto saveReplay(Long userId, CommentReplayDto commentReplayDto) {
        Comment comment = new Comment();
        Post post = postDao.selectPost(commentReplayDto.getPostId());
        if(userId == null || post == null) return null;
        comment.setPostId(post);
        comment.setUseId(userId);
        comment.setContent(commentReplayDto.getContent());
        comment.setCommentDepth(1);

        Comment parentComment = commentDao.selectedComment(commentReplayDto.getParentComment());
        if(parentComment == null) throw new CustomException(ErrorCode.NOT_EXISTS_PARENT_COMMENT);
        comment.setParentComment(parentComment);
        comment.setCommentGroup(parentComment.getCommentGroup());
        return new CommentResponseDto(commentDao.saveComment(comment));
    }

    @Override
    public List<CommentResponseDto> getCommentById(Long postId) {
        List<Comment> comments = commentDao.getCommentById(postId);

        List<CommentResponseDto> commentResponseDtos = comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return commentResponseDtos;
    }

    @Override
    public CommentResponseDto updateCommentContent(Long userId, CommentUpdateDto commentUpdateDto) {
        return new CommentResponseDto(commentDao.updateCommentContent(userId, commentUpdateDto.getCommentId(), commentUpdateDto.getContent()));
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        commentDao.deleteComment(userId, commentId);
    }
}
