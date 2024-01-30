package com.demo.preorder.user.controller;

import com.demo.preorder.user.dto.TokenRefreshDto;
import com.demo.preorder.user.entity.Jwt;
import com.demo.preorder.user.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<Jwt> tokenRefresh(@RequestBody TokenRefreshDto tokenRefreshDto) {
        Jwt jwt = userService.refreshToken(tokenRefreshDto.getRefreshToken());
        if (jwt == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
        return ResponseEntity.ok(jwt);
    }
}