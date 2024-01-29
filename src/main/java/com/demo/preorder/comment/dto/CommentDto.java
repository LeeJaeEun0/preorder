package com.demo.preorder.comment.dto;

import com.demo.preorder.comment.entity.Comment;
import com.demo.preorder.member.entity.User;
import com.demo.preorder.post.entity.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long id;

    private Long postId;

    private String content;

}
