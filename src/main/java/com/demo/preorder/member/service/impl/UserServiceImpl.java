package com.demo.preorder.member.service.impl;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.model.UserDao;
import com.demo.preorder.member.model.UserDto;
import com.demo.preorder.member.repository.UserRepository;
import com.demo.preorder.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkEmail(UserDto userDto) {
        String email = userDto.getEmail();
        boolean is_email = userDao.checkEmail(email);

        return is_email;
    }

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        // 비밀번호 암호화 해서 저장
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setImage(userDto.getImage());
        user.setGreeting(user.getGreeting());
        return userDao.insertUser(user);
    }

    @Override
    public UserDto changeUserContent(Long userId, UserDto userDto) throws Exception {
        return null;
    }

    @Override
    public UserDto changeUserPassword(Long userId, UserDto userDto) throws Exception {
        return null;
    }

    @Override
    public void deleteUser(Long userId) throws Exception {

    }
}
