package com.demo.preorder.comment.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.comment.dao.CommentDao;
import com.demo.preorder.comment.dto.CommentDeleteDto;
import com.demo.preorder.comment.dto.CommentDto;
import com.demo.preorder.comment.dto.CommentReplayDto;
import com.demo.preorder.comment.dto.CommentUpdateDto;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.service.CommentService;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.user.entity.User;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
   private final PostDao postDao;

   private final ActivityClient activityClient;

   private final CommentDao commentDao;

    private final FollowDao followDao;

    @Override
    public Comment saveComment(Long userId,CommentDto commentDto) {
        Comment comment = new Comment();
        User user = activityClient.findUser(userId);
        Post post = postDao.selectPost(commentDto.getPostId());
        if(user == null || post == null) return null;
        comment.setPostId(post);
        comment.setUseId(user);
        comment.setContent(commentDto.getContent());
        comment.setCommentDepth(0);
        int groupId = commentDao.findGroupId();
        comment.setCommentGroup(groupId+1);

        Comment saved = commentDao.saveComment(comment);

        List<Follow> followList = followDao.findFollowing(saved.getUseId().getId());

        if (followList!= null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getPostId().getUserId());
                newsfeedClientDto.setType("comment");
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

        List<Follow> followList2 = followDao.findFollower(saved.getUseId().getId());

        if (followList!= null) {

            for (Follow follows : followList2) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getUserId());
                newsfeedClientDto.setSenderId(saved.getPostId().getUserId());
                newsfeedClientDto.setType("comment");
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

        return saved;
    }

    @Override
    public Comment insertComment(Long userId, CommentReplayDto commentReplayDto) {
        Comment comment = new Comment();
        User user = activityClient.findUser(userId);
        Post post = postDao.selectPost(commentReplayDto.getPostId());
        if(user == null || post == null) return null;
        comment.setPostId(post);
        comment.setUseId(user);
        comment.setContent(commentReplayDto.getContent());
        comment.setCommentDepth(1);

        Comment parentComment = commentDao.selectedComment(commentReplayDto.getParentComment());
        if(parentComment == null) return null;
        comment.setParentComment(parentComment);
        comment.setCommentGroup(parentComment.getCommentGroup());
        return commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> selectComment(CommentDto commentDto) {
        return commentDao.selectComment(commentDto.getPostId());
    }

    @Override
    public Comment changeCommentContent(Long userId, CommentUpdateDto commentUpdateDto) {
        return commentDao.changeCommentContent(userId, commentUpdateDto.getCommentId(), commentUpdateDto.getContent());
    }

    @Override
    public void deleteComment(Long userId, CommentDeleteDto commentDeleteDto) throws Exception {
        commentDao.deleteComment(userId, commentDeleteDto.getCommentId());
    }
}
