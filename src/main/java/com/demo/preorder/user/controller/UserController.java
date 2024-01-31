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

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDTO) {
        boolean is_email = userService.checkEmail(emailDTO);
        if (is_email) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일을 인증 할 수 없습니다");
        }
    }

    @PostMapping("/join")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User user = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<?> updateProfile(@PathVariable Long userId,@RequestBody ProfileDto profileDto) throws Exception {
        User user = userService.changeUserProfile(userId,profileDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/password/{userId}")
    public ResponseEntity<?> updatePassword(@PathVariable Long userId,@RequestBody PasswordDto passwordDto) throws Exception {
        User user = userService.changeUserPassword(userId,passwordDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
