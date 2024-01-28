package com.demo.preorder.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private Long postId;

    private Long userId;

    private String contents;
}
