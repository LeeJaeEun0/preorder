package com.demo.preorder.comment.repository;

import com.demo.preorder.comment.entity.GreatComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreatCommentRepository extends JpaRepository<GreatComment, Long> {
    List<GreatComment> findByCommentIdId(Long commentId);
}
