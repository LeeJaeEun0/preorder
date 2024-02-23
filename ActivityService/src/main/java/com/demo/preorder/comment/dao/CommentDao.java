package com.demo.preorder.comment.dao;

import com.demo.preorder.comment.entity.Comment;

import java.util.List;

public interface CommentDao {
    Comment saveComment(Comment comment);

    Integer findGroupId();

    Comment selectedComment(Long commentId);

    List<Comment> selectComment(Long postId);
    Comment updateCommentContent(Long userId, Long commentId, String content);

    void deleteComment(Long userId, Long commentId) throws Exception;

}
