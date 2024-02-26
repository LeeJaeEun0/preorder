package com.demo.preorder.comment.service;

import com.demo.preorder.comment.dto.*;

import java.util.List;

public interface CommentService {
    CommentResponseDto saveComment(Long userId, CommentDto commentDto);

    CommentResponseDto saveReplay(Long userId, CommentReplayDto commentReplayDto);

    List<CommentResponseDto> getCommentById(Long postId);

    CommentResponseDto updateCommentContent(Long userId, CommentUpdateDto commentUpdateDto);

    void deleteComment(Long userId, Long commentId);
}
