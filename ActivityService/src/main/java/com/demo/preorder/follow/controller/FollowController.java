package com.demo.preorder.follow.controller;

import com.demo.preorder.client.service.UserServiceClient;
import com.demo.preorder.follow.dto.FollowResponseDto;
import com.demo.preorder.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
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

    private final UserServiceClient userServiceClient;

    @PostMapping("/{followingId}")
    public ResponseEntity<?> saveFollow(@RequestHeader Map<String, String> httpHeaders,
                                        @PathVariable("followingId") Long followingId) {
        ResponseEntity<Long> responseEntity = userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        FollowResponseDto followResponseDto = followService.saveFollow(userId, followingId);
        if (followResponseDto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(followResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("팔로우 실패 했습니다");
        }

    }

    @DeleteMapping("/{followingId}")
    public ResponseEntity<?> deleteFollow(@RequestHeader Map<String, String> httpHeaders,
                                          @PathVariable("followingId") Long followingId) throws Exception {
        ResponseEntity<Long> responseEntity = userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        followService.deleteFollow(userId, followingId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/follower")
    public ResponseEntity<?> findFollower(@RequestParam("followingId") Long followingId) {
        List<FollowResponseDto> follows = followService.findFollower(followingId);
        return ResponseEntity.status(HttpStatus.OK).body(follows);
    }

    @GetMapping("/following")
    public ResponseEntity<?> findFollowing(@RequestHeader Map<String, String> httpHeaders) {
        ResponseEntity<Long> responseEntity = userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        List<FollowResponseDto> follows = followService.findFollowing(userId);
        return ResponseEntity.status(HttpStatus.OK).body(follows);
    }
}
