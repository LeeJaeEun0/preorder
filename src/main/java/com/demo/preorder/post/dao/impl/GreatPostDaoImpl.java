package com.demo.preorder.post.dao.impl;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.repository.UserRepository;
import com.demo.preorder.post.dao.GreatPostDao;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.repository.GreatPostRepository;
import com.demo.preorder.post.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GreatPostDaoImpl implements GreatPostDao {

    private final GreatPostRepository greatPostRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public GreatPostDaoImpl(GreatPostRepository greatPostRepository, UserRepository userRepository, PostRepository postRepository) {
        this.greatPostRepository = greatPostRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public GreatPost saveGreatPost(Long userId, Long postId) {
        GreatPost greatPost = new GreatPost();
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalUser==null || optionalPost==null) return null;
        User user = optionalUser.get();
        Post post = optionalPost.get();

        greatPost.setUserId(user);
        greatPost.setPostId(post);
        return greatPostRepository.save(greatPost);
    }

    @Override
    public void deleteGreatPost(Long userId, Long greatPostId) {
        Optional<GreatPost> optionalGreatPost = greatPostRepository.findById(greatPostId);
        if(optionalGreatPost.isPresent()){
            GreatPost greatPost = optionalGreatPost.get();
            if(greatPost.getUserId().getId().equals(userId))
                greatPostRepository.delete(greatPost);
        }

    }

    @Override
    public List<GreatPost> greatPostList(Long postId) {
        return greatPostRepository.findByPostIdId(postId);

    }
}
