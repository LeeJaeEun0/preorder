package com.demo.preorder.comment.dao.impl;

import com.demo.preorder.comment.dao.GreatCommentDao;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.comment.repository.CommentRepository;
import com.demo.preorder.comment.repository.GreatCommentRepository;
import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.repository.UserRepository;
import com.demo.preorder.post.entity.GreatPost;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GreatCommentDaoImpl implements GreatCommentDao {

    private final GreatCommentRepository greatCommentRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    public GreatCommentDaoImpl(GreatCommentRepository greatCommentRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.greatCommentRepository = greatCommentRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public GreatComment saveGreatComment(Long userId, Long commentId) {
        GreatComment greatComment = new GreatComment();
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalUser==null || optionalComment==null) return null;
        User user = optionalUser.get();
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
