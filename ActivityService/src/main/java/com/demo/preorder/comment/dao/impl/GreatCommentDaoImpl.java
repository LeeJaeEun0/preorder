package com.demo.preorder.comment.dao.impl;

import com.demo.preorder.comment.dao.GreatCommentDao;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.repository.CommentRepository;
import com.demo.preorder.comment.repository.GreatCommentRepository;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GreatCommentDaoImpl implements GreatCommentDao {

    private final GreatCommentRepository greatCommentRepository;

    private final CommentRepository commentRepository;

    @Override
    public GreatComment saveGreatComment(Long userId, Long commentId) {
        GreatComment greatComment = new GreatComment();

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()) throw new CustomException(ErrorCode.INVALID_COMMENT);
        Comment comment = optionalComment.get();

        greatComment.setUserId(userId);
        greatComment.setCommentId(comment);
        return greatCommentRepository.save(greatComment);

    }


    @Override
    public void deleteGreatComment(Long userId, Long greatCommentId) {
        Optional<GreatComment> optionalGreatComment = greatCommentRepository.findById(greatCommentId);
        if(optionalGreatComment.isPresent()){
            GreatComment greatComment = optionalGreatComment.get();
            if(greatComment.getUserId().equals(userId))
                greatCommentRepository.delete(greatComment);
            else
                throw new CustomException(ErrorCode.DO_NOT_MATCH_ID);
        }else {
            throw new CustomException(ErrorCode.NOT_EXISTS_GREAT_COMMIT);
        }
    }
}
