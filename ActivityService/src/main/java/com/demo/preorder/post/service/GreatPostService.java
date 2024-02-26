package com.demo.preorder.post.service;

import com.demo.preorder.post.dto.GreatPostResponseDto;
import com.demo.preorder.post.entity.GreatPost;

import java.util.List;

public interface GreatPostService {
    GreatPostResponseDto saveGreatPost(Long userId, Long postId);

    void deleteGreatPost(Long userId, Long postId);

    List<GreatPost> greatPostList(Long postId);
}
