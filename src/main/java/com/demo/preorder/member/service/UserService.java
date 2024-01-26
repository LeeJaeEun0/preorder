package com.demo.preorder.member.service;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.model.UserDao;
import com.demo.preorder.member.model.UserDto;

public interface UserService {

    boolean checkEmail(UserDto userDto);


    UserDto getUser(Long userId);

    User saveUser(UserDto userDto);

    UserDto changeUserContent(Long userId, UserDto userDto) throws Exception;

    UserDto changeUserPassword(Long userId, UserDto userDto) throws Exception;
    void deleteUser(Long userId) throws Exception;
}
