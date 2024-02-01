package com.demo.preorder.profile.controller;

import com.demo.preorder.profile.dto.ProfileDto;
import com.demo.preorder.profile.entity.Profile;
import com.demo.preorder.profile.service.ProfileService;
import com.demo.preorder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveProfile(@RequestHeader Map<String, String> httpHeaders,
                                         @RequestBody ProfileDto profileDto){
        Long userId = userService.findUserId(httpHeaders);
        Profile profile = profileService.saveProfile(userId, profileDto);
        if(profile != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(profile);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("프로필 작성에 실패했습니다.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestHeader Map<String, String> httpHeaders,
                                           @RequestBody ProfileDto profileDto){
        Long userId = userService.findUserId(httpHeaders);
        Profile profile = profileService.updateProfile(userId, profileDto);
        if(profile != null){
            return  ResponseEntity.status(HttpStatus.OK).body(profile);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("프로필 수정에 실패했습니다.");
        }
    }
}
