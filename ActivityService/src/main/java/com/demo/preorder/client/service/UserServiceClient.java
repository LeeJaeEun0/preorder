package com.demo.preorder.client.service;

import com.demo.preorder.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@ComponentScan
@FeignClient(name = "userService", url = "${user.service.url}")
public interface UserServiceClient {
    @GetMapping("/api/internal/users/user")
    ResponseEntity<Long> findUserId(@RequestHeader Map<String, String> httpHeaders);

    @GetMapping("/api/internal/users/member")
    ResponseEntity<User> findUser(@RequestParam("userId") Long userId);
}
