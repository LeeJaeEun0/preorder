package com.demo.preorder.member.model;

import com.demo.preorder.member.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean checkEmail(String email);
    User insertUser(User user);
    Optional<User> selectUser(Long userid);
    public User updateUser(Long userId, String name,String password, String image, String greeating) throws Exception;
    User updateUserPassword(Long userId, String password) throws Exception;
    public void deletetUser(Long userId) throws Exception;
}
