package com.demo.preorder.profile.controller;

import com.demo.preorder.profile.dto.ProfileDto;
import com.demo.preorder.profile.entity.Profile;
import com.demo.preorder.profile.service.ProfileService;
import com.demo.preorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveProfile(@RequestHeader Map<String, String> httpHeaders,
                                         @ModelAttribute ProfileDto profileDto) throws IOException {
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
                                           @ModelAttribute ProfileDto profileDto) throws IOException {
        Long userId = userService.findUserId(httpHeaders);
        Profile profile = profileService.updateProfile(userId, profileDto);
        if(profile != null){
            return  ResponseEntity.status(HttpStatus.OK).body(profile);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("프로필 수정에 실패했습니다.");
        }
    }
}
