package com.demo.preorder.post.service;

import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;

import java.util.List;

public interface PostService {

    Post savePost(Long userId,PostDto postDto);

    Post selectPost(Long postId);

    List<Post> listPost();

    List<Post> searchPost(SearchwordDto searchwordDto);

    Post changePost(Long userId,PostDto postDto);

    void deletePost(Long UserId,Long postId) throws Exception;
}
