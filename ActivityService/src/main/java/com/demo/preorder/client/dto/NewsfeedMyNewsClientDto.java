package com.demo.preorder.client.dto;

import com.demo.preorder.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewsfeedMyNewsClientDto {
    private User userId;

    private User writerId;

    private String type;

    private Long PostId;
}
