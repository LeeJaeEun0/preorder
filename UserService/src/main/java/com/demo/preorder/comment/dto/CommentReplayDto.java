package com.demo.preorder.comment.dto;

import com.demo.preorder.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReplayDto {

    private Long postId;

    private Long parentComment;

    private String content;

}
