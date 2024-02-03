package com.demo.preorder.comment.repository;

import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdId(Long postId);

    Comment findTopByOrderByCommentGroupDesc();
}
