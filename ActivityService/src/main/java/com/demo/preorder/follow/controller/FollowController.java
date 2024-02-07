package com.demo.preorder.follow.controller;

import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dto.FollowDto;
import com.demo.preorder.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/followers")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    private final ActivityClient activityClient;

    @PostMapping
    public ResponseEntity<?> saveFollow(@RequestHeader Map<String, String> httpHeaders,
                                        @RequestBody FollowDto followDto){
        Long userId = activityClient.findUserId(httpHeaders);
        FollowDto followDto1 = followService.saveFollow(userId,followDto);
        if(followDto1 != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(followDto1);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("팔로우 실패 했습니다");
        }

    }
    @DeleteMapping
    public ResponseEntity<Follow> deleteFollow(@RequestHeader Map<String, String> httpHeaders,
                                               @RequestBody FollowDto followDto) throws Exception {
        Long userId = activityClient.findUserId(httpHeaders);
        followService.deleteFollow(userId,followDto);
        return  null;
    }
    @GetMapping("/follower")
    public ResponseEntity<List<Follow>> findFollower(@RequestBody FollowDto followDto){
        List<Follow> follows = followService.findFollower(followDto);
        return  ResponseEntity.status(HttpStatus.OK).body(follows);
    }
    @GetMapping("/following")
    public ResponseEntity<List<Follow>> findFollowing(@RequestHeader Map<String, String> httpHeaders,
                                                      @RequestBody FollowDto followDto){
        Long userId = activityClient.findUserId(httpHeaders);
        List<Follow> follows = followService.findFollowing(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(follows);
    }
}
