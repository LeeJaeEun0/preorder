package com.demo.preorder.user.controller;

import com.demo.preorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/internal/")
@RequiredArgsConstructor
public class RestTemplateController {

    private final UserService userService;
    // 받은 토큰에서 이메일 추출해 userId 리턴
    @GetMapping
    public ResponseEntity<?> findUserId(@RequestHeader Map<String, String> httpHeaders){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserId(httpHeaders));
    }

    @GetMapping("user")
    public ResponseEntity<?> findUser(@RequestBody Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }


}
