package com.demo.preorder.comment.dao.impl;

import com.demo.preorder.comment.dao.CommentDao;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.repository.CommentRepository;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CommentDaoImpl implements CommentDao {


    private final CommentRepository commentRepository;
    public CommentDaoImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
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
    public Comment insertComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment selectedComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
        }
        return null;
    }

    @Override
    public List<Comment> selectComment(Long postId) {

        return commentRepository.findByPostIdId(postId);
    }

    @Override
    public Comment changeCommentContent(Long userId, Long commentId, String content) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();

            if(comment.getUseId().getId().equals(userId)){
                comment.setContent(content);
                return commentRepository.save(comment);
            }
        }
        return null;
    }

    @Override
    public void deleteComment(Long userId, Long commentId) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();

            if(comment.getUseId().getId().equals(userId)){
                commentRepository.delete(comment);
            }else{
                throw new Exception();
            }
        }
    }
}
