package com.demo.preorder.post.service.impl;

import com.demo.preorder.post.dao.GreatPostDao;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.dto.GreatPostDto;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.service.GreatPostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreatPostServiceImpl implements GreatPostService {
    private final GreatPostDao greatPostDao;

    public GreatPostServiceImpl(PostDao postDao, GreatPostDao greatPostDao) {
        this.greatPostDao = greatPostDao;
    }

    @Override
    public GreatPost saveGreatPost(Long userId, GreatPostDto greatPostDto) {
        return greatPostDao.saveGreatPost(userId, greatPostDto.getPostId());
    }

    @Override
    public void deleteGreatPost(Long userId, GreatPostDto greatPostDto) {
        greatPostDao.deleteGreatPost(userId, greatPostDto.getGreatPostId());
    }

    @Override
    public List<GreatPost> greatPostList(GreatPostDto greatPostDto) {
        return greatPostDao.greatPostList(greatPostDto.getPostId());
    }
}
