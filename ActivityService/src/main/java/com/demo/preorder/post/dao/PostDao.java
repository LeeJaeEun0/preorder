package com.demo.preorder.post.dao;

import com.demo.preorder.post.entity.Post;

import java.util.List;

public interface PostDao {
    Post savePost(Post post);

    Post selectPost(Long postId);

    List<Post> listPost();

    List<Post> searchPost(String searchWord);

    Post updatePost(Long userId, Long PostId, String contents);

    void deletePost(Long userID, Long postId);

}
