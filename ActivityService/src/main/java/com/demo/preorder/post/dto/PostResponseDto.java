package com.demo.preorder.post.dto;

import com.demo.preorder.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long userId;

    private String content;

    public PostResponseDto(Post post) {
        this.userId = post.getUserId();
        this.content = post.getContents();
    }
}
