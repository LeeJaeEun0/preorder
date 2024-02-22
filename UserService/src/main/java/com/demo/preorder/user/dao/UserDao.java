package com.demo.preorder.user.dao;

import com.demo.preorder.user.entity.User;
import java.util.Optional;

public interface UserDao {
    User findUser(Long userId);
    boolean checkEmail(String email);
    //void deleteEmailCertification(String email);
    Long findUserId(String email);
    Optional<User> selectUser(Long userid);
    public User updateUserProfile(Long userId, String name);
    User updateUserPassword(Long userId, String oldPassword, String newPassword) ;
    public void deleteUser(Long userId) ;
}
