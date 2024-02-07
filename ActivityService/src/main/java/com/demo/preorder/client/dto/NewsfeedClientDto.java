package com.demo.preorder.client.dto;

import com.demo.preorder.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsfeedClientDto {
    private User userId;

    private User senderId;

    private String type;

    private Long targetId;
}
