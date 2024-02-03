package com.demo.preorder.user.dto;

import com.demo.preorder.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegisterDto {
    private String userEmail;

    private String password;

    private String username;

    public User toEntity(){
        return User.builder()
                .name(username)
                .email(userEmail)
                .password(password)
                .build();
    }
}