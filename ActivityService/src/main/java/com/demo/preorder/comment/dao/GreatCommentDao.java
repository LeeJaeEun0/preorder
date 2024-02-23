package com.demo.preorder.comment.dao;

import com.demo.preorder.comment.entity.GreatComment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GreatCommentDao {
    GreatComment saveGreatComment(Long userId, Long commentId);


    void deleteGreatComment(Long userId, Long greatPostId);

}
