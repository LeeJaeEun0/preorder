package com.demo.preorder.user.dao.impl;

import com.demo.preorder.cofig.PasswordEncoder;
import com.demo.preorder.user.dao.UserDao;
import com.demo.preorder.user.entity.User;
import com.demo.preorder.user.exception.CustomException;
import com.demo.preorder.user.exception.ErrorCode;
import com.demo.preorder.user.repository.EmailCertificationRepository;
import com.demo.preorder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new CustomException(ErrorCode.INVALID_ID);
        }
    }

    @Override
    public boolean checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public Long findUserId(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return user.getId();
        }else {
            throw new CustomException(ErrorCode.INVALID_EMAIL);
        }
    }

    @Override
    public Optional<User> selectUser(Long userId){
        return userRepository.findById(userId);
    }

    @Override
    public User updateUserProfile(Long userId, String name) {
        Optional<User> selectUser = userRepository.findById(userId);
        if (selectUser.isPresent()) {
            User user = selectUser.get();
            user.setName(name);
            return userRepository.save(user);
        } else {
            throw new CustomException(ErrorCode.INVALID_ID);
        }
    }
    @Override
    public User updateUserPassword(Long userId, String oldPassword,String newPassword)  {
        Optional<User> selectUser = userRepository.findById(userId);
        if (selectUser.isPresent()) {

            User user = selectUser.get();
            String password = user.getPassword();

            if (password.equals(passwordEncoder.encrypt(user.getEmail(),oldPassword))){
                user.setPassword(passwordEncoder.encrypt(user.getEmail(),newPassword));
            }else{
                throw new CustomException(ErrorCode.INVALID_PASSWORD);
            }

            return userRepository.save(user);
        } else {
            throw new CustomException(ErrorCode.INVALID_ID);
        }
    }
    @Override
    public void deleteUser(Long userId) {
        Optional<User> deleteUser = userRepository.findById(userId);
        if (deleteUser.isPresent()) {
            User user = deleteUser.get();
            userRepository.delete(user);
        } else {
            throw new CustomException(ErrorCode.INVALID_ID);
        }
    }
}
