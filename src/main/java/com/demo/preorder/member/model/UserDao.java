package com.demo.preorder.member.model;

import com.demo.preorder.member.entity.User;
import java.util.Optional;

public interface UserDao {
    User findUser(Long userId);
    boolean checkEmail(String email);
    //void deleteEmailCertification(String email);
    User insertUser(User user);
    Optional<User> selectUser(Long userid);
    public User updateUserProfile(Long userId, String name, String image, String greeating) throws Exception;
    User updateUserPassword(Long userId, String oldPassword, String newPassword) throws Exception;
    public void deletetUser(Long userId) throws Exception;
}
