package com.demo.preorder.user.controller;

import com.demo.preorder.user.dto.AccessTokenDto;
import com.demo.preorder.user.dto.TokenRefreshDto;
import com.demo.preorder.user.entity.Jwt;
import com.demo.preorder.user.service.JwtService;
import com.demo.preorder.user.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final JwtService jwtService;

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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader Map<String, String> httpHeaders) {
        String authorizationHeader = httpHeaders.get("authorization"); // 헤더 이름은 소문자로 매핑됩니다.
        String accessToeken ="";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // "Bearer " 접두어 제거하여 액세스 토큰만 추출
            accessToeken = authorizationHeader.substring(7);
        }

        jwtService.logout(accessToeken);
        return ResponseEntity.ok().body("ok");
    }
}