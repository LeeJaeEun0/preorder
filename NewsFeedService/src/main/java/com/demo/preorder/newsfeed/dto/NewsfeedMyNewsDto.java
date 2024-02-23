package com.demo.preorder.newsfeed.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewsfeedMyNewsDto {
    private Long userId;

    private Long writerId;

    private String type;

    private Long PostId;
}
