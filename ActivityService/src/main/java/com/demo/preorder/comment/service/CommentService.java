package com.demo.preorder.comment.service;

import com.demo.preorder.comment.dto.*;
import com.demo.preorder.comment.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentResponseDto saveComment(Long userId,CommentDto commentDto);

    CommentResponseDto saveReplay(Long userId, CommentReplayDto commentReplayDto);

    List<Comment> selectComment(Long postId);

    CommentResponseDto updateCommentContent(Long userId, CommentUpdateDto commentUpdateDto);

    void deleteComment(Long userId, Long commentId) ;
}
