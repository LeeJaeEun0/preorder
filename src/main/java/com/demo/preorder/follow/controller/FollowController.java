package com.demo.preorder.follow.controller;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dto.FollowDto;
import com.demo.preorder.follow.service.FollowService;
import com.demo.preorder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/followers")
public class FollowController {
    private final FollowService followService;

    private final UserService userService;

    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveFollow(@RequestHeader Map<String, String> httpHeaders,
                                        @RequestBody FollowDto followDto){
        Long userId = userService.findUserId(httpHeaders);
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
        Long userId = userService.findUserId(httpHeaders);
        followService.deleteFollow(userId,followDto);
        return  null;
    }
    @GetMapping("/followedme")
    public ResponseEntity<List<Follow>> whoFollowedMe(@RequestBody FollowDto followDto){
        List<Follow> follows = followService.whoFollowedMe(followDto);
        return  ResponseEntity.status(HttpStatus.OK).body(follows);
    }
    @GetMapping("/iFollow")
    public ResponseEntity<List<Follow>> peopleIFollow(@RequestHeader Map<String, String> httpHeaders,
                                                      @RequestBody FollowDto followDto){
        Long userId = userService.findUserId(httpHeaders);
        List<Follow> follows = followService.peopleIFollow(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(follows);
    }
}
