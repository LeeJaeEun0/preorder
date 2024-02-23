package com.demo.preorder.comment.dto;

import com.demo.preorder.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long userId;
    private String comment;

    public CommentResponseDto(Comment comment){
        this.userId = comment.getUseId();
        this.comment = comment.getContent();

    }

}
