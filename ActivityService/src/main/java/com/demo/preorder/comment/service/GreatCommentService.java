package com.demo.preorder.comment.service;

import com.demo.preorder.comment.dto.GreatCommentResponseDto;

import java.util.List;

public interface GreatCommentService {
    GreatCommentResponseDto saveGreatComment(Long userId, Long commentId);

    void deleteGreatComment(Long userId, Long greatCommentId);

}
