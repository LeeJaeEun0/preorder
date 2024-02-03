package com.demo.preorder.comment.service;

import com.demo.preorder.comment.dto.CommentDeleteDto;
import com.demo.preorder.comment.dto.CommentDto;
import com.demo.preorder.comment.dto.CommentReplayDto;
import com.demo.preorder.comment.dto.CommentUpdateDto;
import com.demo.preorder.comment.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(Long userId,CommentDto commentDto);

    Comment insertComment(Long userId, CommentReplayDto commentReplayDto);

    List<Comment> selectComment(CommentDto commentDto);
    Comment changeCommentContent(Long userId, CommentUpdateDto commentUpdateDto);

    void deleteComment(Long userId, CommentDeleteDto commentDeleteDto) throws Exception;
}
