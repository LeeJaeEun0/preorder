package com.demo.preorder.follow.service;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.model.FollowDto;

import java.util.List;

public interface FollowService {
    FollowDto saveFollow(FollowDto followDto);

    void deleteFollow(FollowDto followDto) throws Exception;

    List<Follow> whoFollowedMe (FollowDto followDto);

    List<Follow> peopleIFollow(FollowDto followDto);

}
