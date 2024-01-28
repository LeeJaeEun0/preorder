package com.demo.preorder.member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDto {
    private String oldPassword;

    private String newPassword;
}
