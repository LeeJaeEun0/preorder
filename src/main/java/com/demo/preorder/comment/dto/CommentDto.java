package com.demo.preorder.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long id;

    private Long postId;

    private String content;

}
