package com.demo.preorder.profile.controller;

import com.demo.preorder.profile.dto.ProfileDto;
import com.demo.preorder.profile.entity.Profile;
import com.demo.preorder.profile.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private ProfileService profileService;
    @PostMapping("/{userId}")
    public ResponseEntity<?> saveProfile(@PathVariable Long userId, ProfileDto profileDto){
        Profile profile = profileService.saveProfile(userId, profileDto);
        if(profile != null){
            return  ResponseEntity.status(HttpStatus.OK).body(profile);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("프로필 작성에 실패했습니다.");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(@PathVariable Long userId, ProfileDto profileDto){
        Profile profile = profileService.updateProfile(userId, profileDto);
        if(profile != null){
            return  ResponseEntity.status(HttpStatus.OK).body(profile);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("프로필 수정에 실패했습니다.");
        }
    }
}
