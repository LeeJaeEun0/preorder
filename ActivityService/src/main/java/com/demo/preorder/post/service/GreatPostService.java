package com.demo.preorder.post.service;

import com.demo.preorder.post.dao.GreatPostDao;
import com.demo.preorder.post.dto.GreatPostDto;
import com.demo.preorder.post.entity.GreatPost;

import java.util.List;

public interface GreatPostService {
    GreatPost saveGreatPost(Long userId, GreatPostDto greatPostDto);

    void deleteGreatPost(Long userId, GreatPostDto greatPostDto);

    List<GreatPost> greatPostList(GreatPostDto greatPostDto);
}
