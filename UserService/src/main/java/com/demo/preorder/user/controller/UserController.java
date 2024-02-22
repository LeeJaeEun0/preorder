package com.demo.preorder.user.controller;

import com.demo.preorder.user.entity.User;
import com.demo.preorder.user.dto.EmailDto;
import com.demo.preorder.user.dto.PasswordDto;
import com.demo.preorder.user.dto.ProfileDto;
import com.demo.preorder.user.dto.UserDto;
import com.demo.preorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDTO) {
        log.info("info log: controller");
        log.info("info log = {}", emailDTO.getEmail());
        boolean is_email = userService.checkEmail(emailDTO);
        if (is_email) {
            return ResponseEntity.status(HttpStatus.CREATED).body(emailDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일을 인증 할 수 없습니다");
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestHeader Map<String, String> httpHeaders,
                                           @RequestBody ProfileDto profileDto) {
        Map<String, String> httpHeader = httpHeaders;
        Long userId = userService.findUserId(httpHeader);
        User user = userService.changeUserProfile(userId,profileDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestHeader Map<String, String> httpHeaders,
                                            @RequestBody PasswordDto passwordDto) {
        Long userId = userService.findUserId(httpHeaders);
        User user = userService.changeUserPassword(userId,passwordDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestHeader Map<String, String> httpHeaders) {
        Long userId = userService.findUserId(httpHeaders);
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
