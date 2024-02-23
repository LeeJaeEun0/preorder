package com.demo.preorder.post.service;

import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.PostResponseDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;

import java.util.List;

public interface PostService {

    PostResponseDto savePost(Long userId,PostDto postDto);

    PostResponseDto selectPost(Long postId);

    List<Post> listPost();

    List<Post> searchPost(SearchwordDto searchwordDto);

    PostResponseDto changePost(Long userId, PostDto postDto);

    void deletePost(Long UserId,Long postId) ;
}
