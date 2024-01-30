package com.demo.preorder.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String image;

    private String greeting;

    private String createdDate;
}
