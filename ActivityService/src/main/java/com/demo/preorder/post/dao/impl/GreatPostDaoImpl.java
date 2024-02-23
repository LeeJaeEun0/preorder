package com.demo.preorder.post.dao.impl;

import com.demo.preorder.client.service.ActivityRestTemplateClient;
import com.demo.preorder.follow.repository.FollowRepository;
import com.demo.preorder.post.dao.GreatPostDao;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.repository.GreatPostRepository;
import com.demo.preorder.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GreatPostDaoImpl implements GreatPostDao {

    private final GreatPostRepository greatPostRepository;

    private final PostRepository postRepository;

    private final FollowRepository followRepository;

    private final ActivityRestTemplateClient activityRestTemplateClient;

    @Override
    public GreatPost saveGreatPost(Long userId, Long postId) {
        GreatPost greatPost = new GreatPost();
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(userId==null || optionalPost==null) return null;
        Post post = optionalPost.get();

        greatPost.setUserId(userId);
        greatPost.setPostId(post);
        return greatPostRepository.save(greatPost);

    }

    @Override
    public void deleteGreatPost(Long userId, Long greatPostId) {
        Optional<GreatPost> optionalGreatPost = greatPostRepository.findById(greatPostId);
        if(optionalGreatPost.isPresent()){
            GreatPost greatPost = optionalGreatPost.get();
            if(greatPost.getUserId().equals(userId))
                greatPostRepository.delete(greatPost);
        }

    }

    @Override
    public List<GreatPost> greatPostList(Long postId) {
        return greatPostRepository.findByPostIdId(postId);

    }
}
