package com.demo.preorder.post.dto;

import com.demo.preorder.comment.dto.GreatCommentResponseDto;
import com.demo.preorder.comment.entity.GreatComment;
import com.demo.preorder.post.entity.GreatPost;
import lombok.Getter;

@Getter
public class GreatPostResponseDto {
    private Long userId;

    private Long postId;

    public GreatPostResponseDto(GreatPost greatPost){
        this.userId = greatPost.getUserId();
        this.postId = greatPost.getPostId().getId();
    }
}
