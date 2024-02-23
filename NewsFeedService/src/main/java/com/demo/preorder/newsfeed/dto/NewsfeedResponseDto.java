package com.demo.preorder.newsfeed.dto;

import com.demo.preorder.newsfeed.entity.Newsfeed;
import lombok.Getter;

@Getter
public class NewsfeedResponseDto {

    private Long userId;

    private Long senderId;

    public NewsfeedResponseDto(Newsfeed newsfeed){
        this.userId = newsfeed.getUserId();
        this.senderId = newsfeed.getSenderId();
    }
}
