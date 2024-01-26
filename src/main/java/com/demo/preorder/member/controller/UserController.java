package com.demo.preorder.member.controller;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.model.UserDto;
import com.demo.preorder.member.service.UserService;
import com.demo.preorder.member.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody UserDto userDto) {
        boolean is_email = userService.checkEmail(userDto);
        if (is_email) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 가입한 사용자 입니다");
        }
    }

    @PostMapping("/join")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User user = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
