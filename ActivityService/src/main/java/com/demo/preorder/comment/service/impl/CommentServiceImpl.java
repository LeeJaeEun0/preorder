package com.demo.preorder.comment.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.dto.NewsfeedMyNewsClientDto;
import com.demo.preorder.client.service.NewsfeedServiceClient;
import com.demo.preorder.client.service.UserServiceClient;
import com.demo.preorder.comment.dao.CommentDao;
import com.demo.preorder.comment.dto.CommentDto;
import com.demo.preorder.comment.dto.CommentReplayDto;
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

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
   private final PostDao postDao;

   private final NewsfeedServiceClient newsfeedServiceClient;

   private final CommentDao commentDao;

    private final FollowDao followDao;

    @Override
    public Comment saveComment(Long userId,CommentDto commentDto) {
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
                    log.info("Info log: Following - userID={} result={}", follows.getUserId(), result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving following for userID={}: {}", follows.getUserId(), e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
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
        newsfeedMyNewsClientDto.setWriterId(saved.getUseId());
        newsfeedMyNewsClientDto.setPostId(saved.getPostId().getId());
        newsfeedMyNewsClientDto.setType("comment");
        try {
            // 외부 서비스 호출
            ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeedMyNews(newsfeedMyNewsClientDto);
            String result = stringResponseEntity.getBody();
            log.info("Info log: newsfeedMyNews - userID={} result={}", newsfeedMyNewsClientDto.getUserId(), result);
        } catch (Exception e) {
            // 오류 발생 시 처리
            log.error("Error saving follower for userID={}: {}", newsfeedMyNewsClientDto.getUserId(), e.getMessage(), e);
            // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
        }



        return saved;
    }

    @Override
    public Comment saveReplay(Long userId, CommentReplayDto commentReplayDto) {
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
        return commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> selectComment(Long postId) {
        return commentDao.selectComment(postId);
    }

    @Override
    public Comment updateCommentContent(Long userId, CommentUpdateDto commentUpdateDto) {
        return commentDao.updateCommentContent(userId, commentUpdateDto.getCommentId(), commentUpdateDto.getContent());
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        commentDao.deleteComment(userId, commentId);
    }
}
