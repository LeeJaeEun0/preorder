package com.demo.preorder.user.controller;

import com.demo.preorder.user.entity.User;
import com.demo.preorder.user.dto.EmailDto;
import com.demo.preorder.user.dto.PasswordDto;
import com.demo.preorder.user.dto.ProfileDto;
import com.demo.preorder.user.dto.UserDto;
import com.demo.preorder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDTO) {
        boolean is_email = userService.checkEmail(emailDTO);
        if (is_email) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일을 인증 할 수 없습니다");
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestHeader Map<String, String> httpHeaders,
                                           @RequestBody ProfileDto profileDto) throws Exception {
        Map<String, String> httpHeader = httpHeaders;
        Long userId = userService.findUserId(httpHeader);
        User user = userService.changeUserProfile(userId,profileDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestHeader Map<String, String> httpHeaders,
                                            @RequestBody PasswordDto passwordDto) throws Exception {
        Long userId = userService.findUserId(httpHeaders);
        User user = userService.changeUserPassword(userId,passwordDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
