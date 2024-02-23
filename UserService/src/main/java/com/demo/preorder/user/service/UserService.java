package com.demo.preorder.user.service;

import com.demo.preorder.user.dto.*;
import com.demo.preorder.user.entity.User;

import java.util.Map;

public interface UserService {

    boolean checkEmail(EmailDto emailDto);

    UserResponseDto getUser(Long userId);

    Long findUserId(Map<String, String> httpHeaders);

    UserResponseDto changeUserProfile(Long userId, ProfileDto profileDto);

    UserResponseDto changeUserPassword(Long userId, PasswordDto passwordDto);
    void deleteUser(Long userId);
}
