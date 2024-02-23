package com.demo.preorder.newsfeed.dto;

import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import lombok.Getter;

@Getter
public class NewsfeedMyNewsResponseDto {

    private Long userId;

    private Long writerId;

    public NewsfeedMyNewsResponseDto(NewsfeedMyNews newsfeedMyNews){
        this.userId = newsfeedMyNews.getUserId();
        this.writerId = newsfeedMyNews.getWriterId();
    }

}
