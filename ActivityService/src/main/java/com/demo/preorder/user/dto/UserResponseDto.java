package com.demo.preorder.user.dto;

import com.demo.preorder.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String email;
    private String username;

    public UserResponseDto(User users){
        this.email = users.getEmail();
        this.username = users.getName();
    }
}