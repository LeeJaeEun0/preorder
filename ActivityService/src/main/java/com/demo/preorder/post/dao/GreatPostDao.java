package com.demo.preorder.post.dao;

import com.demo.preorder.post.entity.GreatPost;

import java.util.List;

public interface GreatPostDao {

    GreatPost saveGreatPost(Long userId, Long postId);

    void deleteGreatPost(Long userId, Long greatPostId);

    List<GreatPost> greatPostList(Long postId);

}
