package com.demo.preorder.comment.service.impl;

import com.demo.preorder.comment.dao.CommentDao;
import com.demo.preorder.comment.dto.CommentDeleteDto;
import com.demo.preorder.comment.dto.CommentDto;
import com.demo.preorder.comment.dto.CommentReplayDto;
import com.demo.preorder.comment.dto.CommentUpdateDto;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.service.CommentService;
import com.demo.preorder.user.dao.UserDao;
import com.demo.preorder.user.entity.User;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
   private final PostDao postDao;

   private final UserDao userDao;

   private final CommentDao commentDao;

    public CommentServiceImpl(PostDao postDao, UserDao userDao, CommentDao commentDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    @Override
    public Comment saveComment(Long userId,CommentDto commentDto) {
        Comment comment = new Comment();
        User user = userDao.findUser(userId);
        Post post = postDao.selectPost(commentDto.getPostId());
        if(user == null || post == null) return null;
        comment.setPostId(post);
        comment.setUseId(user);
        comment.setContent(commentDto.getContent());
        comment.setCommentDepth(0);
        int groupId = commentDao.findGroupId();
        comment.setCommentGroup(groupId+1);
        return commentDao.saveComment(comment);
    }

    @Override
    public Comment insertComment(Long userId, CommentReplayDto commentReplayDto) {
        Comment comment = new Comment();
        User user = userDao.findUser(userId);
        Post post = postDao.selectPost(commentReplayDto.getPostId());
        if(user == null || post == null) return null;
        comment.setPostId(post);
        comment.setUseId(user);
        comment.setContent(commentReplayDto.getContent());
        comment.setCommentDepth(1);

        Comment parentComment = commentDao.selectedComment(commentReplayDto.getParentComment());
        if(parentComment == null) return null;
        comment.setParentComment(parentComment);
        comment.setCommentGroup(parentComment.getCommentGroup());
        return commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> selectComment(CommentDto commentDto) {
        return commentDao.selectComment(commentDto.getPostId());
    }

    @Override
    public Comment changeCommentContent(Long userId, CommentUpdateDto commentUpdateDto) {
        return commentDao.changeCommentContent(userId, commentUpdateDto.getCommentId(), commentUpdateDto.getContent());
    }

    @Override
    public void deleteComment(Long userId, CommentDeleteDto commentDeleteDto) throws Exception {
        commentDao.deleteComment(userId, commentDeleteDto.getCommentId());
    }
}
