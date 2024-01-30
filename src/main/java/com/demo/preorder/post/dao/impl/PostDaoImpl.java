package com.demo.preorder.post.dao.impl;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.repository.FollowRepository;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.repository.NewsfeedIFollowRepository;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostDaoImpl implements PostDao {

    private final PostRepository postRepository;

    private final FollowRepository followRepository;

    private final NewsfeedIFollowRepository newsfeedIFollowRepository;

    public PostDaoImpl(PostRepository postRepository, FollowRepository followRepository, NewsfeedIFollowRepository newsfeedIFollowRepository) {
        this.postRepository = postRepository;
        this.followRepository = followRepository;
        this.newsfeedIFollowRepository = newsfeedIFollowRepository;
    }

    @Override
    @Transactional
    public Post savePost(Post post) {
        Post saved = postRepository.save(post);
        Optional<List<Follow>>optionalFollowList = followRepository.findByFollowingIdId(saved.getUserId().getId());

        if (optionalFollowList.isPresent()) {
            List<Follow> followList = optionalFollowList.get();

            for (Follow follows : followList) {
                NewsfeedIFollow newsfeedIFollow = new NewsfeedIFollow();
                newsfeedIFollow.setUserId(follows.getUserId());
                newsfeedIFollow.setFollowingId(saved.getUserId());
                newsfeedIFollow.setType("post");
                newsfeedIFollow.setTargetId(saved.getId());
                newsfeedIFollowRepository.save(newsfeedIFollow);
            }
        }
        return saved;

    }

    @Override
    public Post selectPost(Long postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        if(findPost.isPresent()){
            Post post = findPost.get();
            return post;
        }
        return null;
    }

    @Override
    public List<Post> listPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> searchPost(String searchWord) {
        return postRepository.findContents(searchWord);
    }

    @Override
    public Post changePost(Long userId,Long postId, String contents) {
        Optional<Post> findPost = postRepository.findById(postId);
        if(findPost.isPresent()){
            Post post = findPost.get();

            if(post.getUserId().getId().equals(userId)) {
                post.setContents(contents);
                return postRepository.save(post);
            }
        }
        return null;
    }

    @Override
    public void deletePost(Long userId,Long postId) throws Exception {
        Optional<Post> deletepost = postRepository.findById(postId);
        if (deletepost.isPresent()) {
            Post post = deletepost.get();

            if(post.getUserId().getId().equals(userId)) postRepository.delete(post);
            else{
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }
}
