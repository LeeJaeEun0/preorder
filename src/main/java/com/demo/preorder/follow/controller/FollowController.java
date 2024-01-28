package com.demo.preorder.follow.controller;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.dto.FollowDto;
import com.demo.preorder.follow.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/followers")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/following")
    public ResponseEntity<?> saveFollow(@RequestBody FollowDto followDto){
        FollowDto followDto1 = followService.saveFollow(followDto);
        if(followDto1 != null){
            return  ResponseEntity.status(HttpStatus.OK).body(followDto1);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("팔로우 실패 했습니다");
        }

    }
    @DeleteMapping
    public ResponseEntity<Follow> deleteFollow(@RequestBody FollowDto followDto) throws Exception {
        followService.deleteFollow(followDto);
        return  null;
    }
    @GetMapping("/followedme")
    public ResponseEntity<List<Follow>> whoFollowedMe(@RequestBody FollowDto followDto){
        List<Follow> follows = followService.whoFollowedMe(followDto);
        return  ResponseEntity.status(HttpStatus.OK).body(follows);
    }
    @GetMapping("iFollow")
    public ResponseEntity<List<Follow>> peopleIFollow(@RequestBody FollowDto followDto){
        List<Follow> follows = followService.peopleIFollow(followDto);
        return  ResponseEntity.status(HttpStatus.OK).body(follows);
    }
}
