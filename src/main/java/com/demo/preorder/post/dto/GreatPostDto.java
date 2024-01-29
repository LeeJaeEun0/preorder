package com.demo.preorder.post.dto;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GreatPostDto {

    private Long greatPostId;

    private Long postId;
}
