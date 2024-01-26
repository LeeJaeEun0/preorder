package com.demo.preorder.member.model.impl;

import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.model.UserDao;
import com.demo.preorder.member.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public User insertUser(User user){
        return userRepository.save(user);
    }

    @Override
    public Optional<User> selectUser(Long userId){
        return userRepository.findById(userId);
    }

    @Override
    public User updateUser(Long userId, String name,String password, String image, String greeating) throws Exception {
        Optional<User> selectUser = userRepository.findById(userId);
        User updateUser;
        if (selectUser.isPresent()) {
            User user = selectUser.get();

            user.setName(name);
            user.setPassword(password);
            user.setImage(image);
            user.setGreeting(greeating);

            updateUser = userRepository.save(user);
        } else {
            throw new Exception();
        }
        return updateUser;

    }
    @Override
    public User updateUserPassword(Long userId, String password) throws Exception {
        Optional<User> selectUser = userRepository.findById(userId);
        User updateUser;
        if (selectUser.isPresent()) {
            User user = selectUser.get();
            user.setPassword(password);

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
