package com.demo.preorder.newsfeed.dto;

import com.demo.preorder.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsfeedFollowingDto {
    private User userId;

    private User followingId;

    private String type;

    private Long targetId;


}
