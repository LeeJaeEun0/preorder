package com.demo.preorder.post.service.impl;

import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    private final ActivityClient activityClient;

    @Override
    public Post savePost(Long userId,PostDto postDto) {
        Post post = new Post();
        if(activityClient.findUser(userId) == null)
            return null;

        post.setUserId(activityClient.findUser(userId));
        post.setContents(postDto.getContents());

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
        return postDao.changePost(userId, postDto.getPostId(),postDto.getContents());
    }

    @Override
    public void deletePost(Long userId,PostDto postDto) throws Exception {
        postDao.deletePost(userId,postDto.getPostId());
    }
}
