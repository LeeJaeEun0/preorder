package com.demo.preorder.user.dao.impl;

import com.demo.preorder.cofig.PasswordEncoder;
import com.demo.preorder.user.dao.UserDao;
import com.demo.preorder.user.entity.User;
import com.demo.preorder.user.repository.EmailCertificationRepository;
import com.demo.preorder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    @Override
    public User findUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User user1 = user.get();
            return user1;
        }
        return null;
    }

    @Override
    public boolean checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        log.info("info log = {}",user);
        if(user.isPresent())
            return true;
        else
            return false;
    }

    @Override
    public Long findUserId(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return user.getId();
        }

        return null;
    }

    @Override
    public Optional<User> selectUser(Long userId){
        return userRepository.findById(userId);
    }

    @Override
    public User updateUserProfile(Long userId, String name) throws Exception {
        Optional<User> selectUser = userRepository.findById(userId);
        User updateUser;
        if (selectUser.isPresent()) {
            User user = selectUser.get();

            user.setName(name);

            updateUser = userRepository.save(user);
        } else {
            throw new Exception();
        }
        return updateUser;

    }
    @Override
    public User updateUserPassword(Long userId, String oldPassword,String newPassword) throws Exception {
        Optional<User> selectUser = userRepository.findById(userId);
        User updateUser;
        if (selectUser.isPresent()) {

            User user = selectUser.get();
            String password = user.getPassword();

            if (password.equals(oldPassword)){
                user.setPassword(newPassword);
            }else{
                throw new Exception();
            }

            updateUser = userRepository.save(user);
        } else {
            throw new Exception();
        }
        return updateUser;

    }
    @Override
    public void deletetUser(Long userId) throws Exception {
        Optional<User> deleteUser = userRepository.findById(userId);
        if (deleteUser.isPresent()) {
            User user = deleteUser.get();

            userRepository.delete(user);
        } else {
            throw new Exception();
        }
    }
}
