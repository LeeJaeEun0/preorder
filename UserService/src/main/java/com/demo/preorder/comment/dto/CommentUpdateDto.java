package com.demo.preorder.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDto {
    private Long commentId;

    private String content;
}
