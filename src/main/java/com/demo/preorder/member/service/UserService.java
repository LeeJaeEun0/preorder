package com.demo.preorder.member.service;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.dto.EmailDto;
import com.demo.preorder.member.dto.PasswordDto;
import com.demo.preorder.member.dto.ProfileDto;
import com.demo.preorder.member.dto.UserDto;

public interface UserService {

    boolean checkEmail(EmailDto emailDto);


    UserDto getUser(Long userId);

    User saveUser(UserDto userDto);

    User changeUserProfile(Long userId, ProfileDto profileDto) throws Exception;

    User changeUserPassword(Long userId, PasswordDto passwordDto) throws Exception;
    void deleteUser(Long userId) throws Exception;
}
