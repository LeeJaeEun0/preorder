package com.demo.preorder.post.service.impl;

import com.demo.preorder.member.dao.UserDao;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.service.PostService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final UserDao userDao;
    public PostServiceImpl(PostDao postDao, UserDao userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @Override
    public Post savePost(Long userId,PostDto postDto) {
        Post post = new Post();
        if(userDao.findUser(userId) == null)
            return null;

        post.setUserId(userDao.findUser(userId));
        post.setContents(post.getContents());
        return postDao.savePost(post);
    }

    @Override
    public Post selectPost(PostDto postDto) {
        Post post = postDao.selectPost(postDto.getPostId());
        return post;
    }

    @Override
    public List<Post> listPost() {
        return postDao.listPost();
    }

    @Override
    public List<Post> searchPost(SearchwordDto searchwordDto) {
        return postDao.searchPost(searchwordDto.getSearchWord());
    }

    @Override
    public Post changePost(Long userId,PostDto postDto) {
        return postDao.changePost(userId, postDto.getContents());
    }

    @Override
    public void deletePost(Long userId,PostDto postDto) throws Exception {
        postDao.deletePost(userId,postDto.getPostId());
    }
}
