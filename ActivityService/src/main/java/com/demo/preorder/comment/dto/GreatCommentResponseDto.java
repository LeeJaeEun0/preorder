package com.demo.preorder.comment.dto;

import com.demo.preorder.comment.entity.GreatComment;
import lombok.Getter;

@Getter
public class GreatCommentResponseDto {
    private Long userId;
    private Long commentId;

    public GreatCommentResponseDto(GreatComment greatComment){
        this.userId = greatComment.getUserId();
        this.commentId = greatComment.getCommentId().getId();
    }
}
