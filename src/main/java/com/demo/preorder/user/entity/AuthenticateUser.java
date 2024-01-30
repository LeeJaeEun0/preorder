package com.demo.preorder.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class AuthenticateUser {
    private String email;
    private Set<Role> roles;
}