package com.demo.preorder.user.controller;


import com.demo.preorder.user.dto.UserRegisterDto;
import com.demo.preorder.user.dto.UserResponseDto;
import com.demo.preorder.user.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.registerUser(userRegisterDto));
    }
}