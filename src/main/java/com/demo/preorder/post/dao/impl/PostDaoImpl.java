package com.demo.preorder.post.dao.impl;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostDaoImpl implements PostDao {

    private final PostRepository postRepository;

    public PostDaoImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
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
    public Post changePost(Long postId, String contents) {
        Optional<Post> findPost = postRepository.findById(postId);
        if(findPost.isPresent()){
            Post post = findPost.get();
            post.setContents(contents);
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public void deletePost(Long userID,Long postId) throws Exception {
        Optional<Post> deletepost = postRepository.findById(postId);
        if (deletepost.isPresent()) {
            Post post = deletepost.get();

            if(post.getUserId().getId() == userID) postRepository.delete(post);
            else{
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }
}
