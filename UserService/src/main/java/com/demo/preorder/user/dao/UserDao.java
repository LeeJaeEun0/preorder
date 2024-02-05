package com.demo.preorder.user.dao;

import com.demo.preorder.user.entity.User;
import java.util.Optional;

public interface UserDao {
    User findUser(Long userId);
    boolean checkEmail(String email);
    //void deleteEmailCertification(String email);
    User findUserId(String email);
    Optional<User> selectUser(Long userid);
    public User updateUserProfile(Long userId, String name) throws Exception;
    User updateUserPassword(Long userId, String oldPassword, String newPassword) throws Exception;
    public void deletetUser(Long userId) throws Exception;
}
