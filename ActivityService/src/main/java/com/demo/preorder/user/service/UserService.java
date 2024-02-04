package com.demo.preorder.user.service;

import com.demo.preorder.user.entity.User;
import com.demo.preorder.user.dto.EmailDto;
import com.demo.preorder.user.dto.PasswordDto;
import com.demo.preorder.user.dto.ProfileDto;
import com.demo.preorder.user.dto.UserDto;

import java.util.Map;

public interface UserService {

    boolean checkEmail(EmailDto emailDto);

    UserDto getUser(Long userId);

    Long findUserId(Map<String, String> httpHeaders);

    User changeUserProfile(Long userId, ProfileDto profileDto) throws Exception;

    User changeUserPassword(Long userId, PasswordDto passwordDto) throws Exception;
    void deleteUser(Long userId) throws Exception;
}