package com.demo.preorder.comment.dao.impl;

import com.demo.preorder.client.service.ActivityRestTemplateClient;
import com.demo.preorder.comment.dao.GreatCommentDao;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.repository.CommentRepository;
import com.demo.preorder.comment.repository.GreatCommentRepository;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.repository.FollowRepository;
import com.demo.preorder.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GreatCommentDaoImpl implements GreatCommentDao {

    private final GreatCommentRepository greatCommentRepository;

    private final CommentRepository commentRepository;

    private final ActivityRestTemplateClient activityRestTemplateClient;
    @Override
    public GreatComment saveGreatComment(Long userId, Long commentId) {
        GreatComment greatComment = new GreatComment();
        User user = activityRestTemplateClient.findUser(userId);
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(user==null || optionalComment==null) return null;
        Comment comment = optionalComment.get();

        greatComment.setUserId(user);
        greatComment.setCommentId(comment);
        return greatCommentRepository.save(greatComment);

    }

    @Override
    public List<GreatComment> greatCommentList(Long commentId) {
        return greatCommentRepository.findByCommentIdId(commentId);

    }

    @Override
    public void deleteGreatComment(Long userId, Long greatCommentId) {
        Optional<GreatComment> optionalGreatComment = greatCommentRepository.findById(greatCommentId);
        if(optionalGreatComment.isPresent()){
            GreatComment greatComment = optionalGreatComment.get();
            if(greatComment.getUserId().getId().equals(userId))
                greatCommentRepository.delete(greatComment);
        }
    }
}
