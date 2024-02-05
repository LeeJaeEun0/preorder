package com.demo.preorder.user.controller;

import com.demo.preorder.user.dto.EmailCertificationDto;
import com.demo.preorder.user.dto.EmailDto;
import com.demo.preorder.user.service.EmailCertificationService;
import com.demo.preorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class EmailCertificationController {
    private final EmailCertificationService emailCertificationService;
    @GetMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailCertificationDto emailCertificationDto) {
        boolean is_email = emailCertificationService.emailCertification(emailCertificationDto);
        if (is_email) {
            return ResponseEntity.status(HttpStatus.OK).body(is_email);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일을 인증 할 수 없습니다");
        }
    }
}
