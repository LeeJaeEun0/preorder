package com.demo.preorder.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsfeedClientDto {
    private Long userId;

    private Long senderId;

    private String type;

    private Long targetId;
}
