package com.demo.preorder.user.dto;


import com.demo.preorder.user.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class UserVerifyResponseDto {
    private boolean isValid;
    private Set<Role> userRole;
}