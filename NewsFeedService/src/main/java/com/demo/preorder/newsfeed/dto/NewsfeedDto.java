package com.demo.preorder.newsfeed.dto;

import com.demo.preorder.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsfeedDto {

    private User userId;

    private User senderId;

    private String type;

    private Long targetId;
}
