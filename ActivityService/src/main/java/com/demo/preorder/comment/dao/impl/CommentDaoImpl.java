package com.demo.preorder.comment.dao.impl;

import com.demo.preorder.comment.dao.CommentDao;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.repository.CommentRepository;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Integer findGroupId() {
        Comment comment = commentRepository.findTopByOrderByCommentGroupDesc();
        if (comment == null) return 1;
        return comment.getCommentGroup();
    }

    @Override
    public Comment selectedComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        } else {
            throw new CustomException(ErrorCode.INVALID_COMMENT);
        }

    }

    @Override
    public List<Comment> getCommentById(Long postId) {
        return commentRepository.findByPostIdId(postId);
    }

    @Override
    public Comment updateCommentContent(Long userId, Long commentId, String content) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (comment.getUseId().equals(userId)) {
                comment.setContent(content);
                return commentRepository.save(comment);
            } else {
                throw new CustomException(ErrorCode.DO_NOT_MATCH_ID);
            }
        } else {
            throw new CustomException(ErrorCode.INVALID_COMMENT);
        }
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (comment.getUseId().equals(userId)) {
                commentRepository.delete(comment);
            } else {
                throw new CustomException(ErrorCode.DO_NOT_MATCH_ID);
            }
        } else {
            throw new CustomException(ErrorCode.INVALID_COMMENT);
        }
    }
}
