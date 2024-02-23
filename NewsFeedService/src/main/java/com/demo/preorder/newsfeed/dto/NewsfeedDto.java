package com.demo.preorder.newsfeed.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsfeedDto {

    private Long userId;

    private Long senderId;

    private String type;

    private Long targetId;
}
