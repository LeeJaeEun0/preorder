package com.demo.preorder.comment.service;

import com.demo.preorder.comment.dto.GreatCommentDto;
import com.demo.preorder.comment.entity.GreatComment;
import java.util.List;

public interface GreatCommentService {
    GreatComment saveGreatComment(Long userId, Long commentId);

    void deleteGreatComment(Long userId, Long greatCommentId);

}
