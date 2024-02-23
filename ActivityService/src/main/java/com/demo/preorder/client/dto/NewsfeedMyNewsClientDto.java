package com.demo.preorder.client.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewsfeedMyNewsClientDto {
    private Long userId;

    private Long writerId;

    private String type;

    private Long PostId;
}
