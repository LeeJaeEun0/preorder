package com.demo.preorder.comment.dao.impl;

import com.demo.preorder.comment.dao.CommentDao;
import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.comment.repository.CommentRepository;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.repository.FollowRepository;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.repository.NewsfeedIFollowRepository;
import com.demo.preorder.newsfeed.repository.NewsfeedMyNewsRepository;
import com.demo.preorder.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CommentDaoImpl implements CommentDao {


    private final CommentRepository commentRepository;

    private final FollowRepository followRepository;

    private final NewsfeedIFollowRepository newsfeedIFollowRepository;

    private final NewsfeedMyNewsRepository newsfeedMyNewsRepository;

    public CommentDaoImpl(CommentRepository commentRepository, PostRepository postRepository, FollowRepository followRepository, NewsfeedIFollowRepository newsfeedIFollowRepository, NewsfeedMyNewsRepository newsfeedMyNewsRepository) {
        this.commentRepository = commentRepository;
        this.followRepository = followRepository;
        this.newsfeedIFollowRepository = newsfeedIFollowRepository;
        this.newsfeedMyNewsRepository = newsfeedMyNewsRepository;
    }

    @Override
    @Transactional
    public Comment saveComment(Comment comment) {
        Comment saved = commentRepository.save(comment);
        Optional<List<Follow>>optionalFollowList = followRepository.findByFollowingIdId(saved.getUseId().getId());

        if (optionalFollowList.isPresent()) {
            List<Follow> followList = optionalFollowList.get();

            for (Follow follows : followList) {
                NewsfeedIFollow newsfeedIFollow = new NewsfeedIFollow();
                newsfeedIFollow.setUserId(follows.getUserId());
                newsfeedIFollow.setFollowingId(saved.getUseId());
                newsfeedIFollow.setType("comment");
                newsfeedIFollow.setTargetId(saved.getId());
                newsfeedIFollowRepository.save(newsfeedIFollow);
            }
        }

        // 포스트를 작성한 사람에게 좋아요 알림
        NewsfeedMyNews newsfeedFollowedMe = new NewsfeedMyNews();
        newsfeedFollowedMe.setUserId(saved.getPostId().getUserId());
        newsfeedFollowedMe.setWriterId(saved.getUseId());
        newsfeedFollowedMe.setType("comment");
        newsfeedFollowedMe.setPostId(saved.getId());
        newsfeedMyNewsRepository.save(newsfeedFollowedMe);
        return saved;
    }

    @Override
    public Integer findGroupId() {
        Comment comment = commentRepository.findTopByOrderByCommentGroupDesc();
        if (comment == null) return 1;
        return comment.getCommentGroup();
    }

    @Override
    public Comment insertComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment selectedComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            return comment;
        }
        return null;
    }

    @Override
    public List<Comment> selectComment(Long postId) {

        return commentRepository.findByPostIdId(postId);
    }

    @Override
    public Comment changeCommentContent(Long userId, Long commentId, String content) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();

            if(comment.getUseId().getId().equals(userId)){
                comment.setContent(content);
                return commentRepository.save(comment);
            }
        }
        return null;
    }

    @Override
    public void deleteComment(Long userId, Long commentId) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();

            if(comment.getUseId().getId().equals(userId)){
                commentRepository.delete(comment);
            }else{
                throw new Exception();
            }
        }
    }
}
