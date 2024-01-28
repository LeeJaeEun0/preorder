package com.demo.preorder.member.dao;

import com.demo.preorder.cofig.PasswordEncoder;
import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.repository.EmailCertificationRepository;
import com.demo.preorder.member.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    public UserDaoImpl(UserRepository userRepository, EmailCertificationRepository emailCertificationRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUser(Long userId) {
//        if(userId == null) return null;
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
    public User updateUserProfile(Long userId, String name, String image, String greeting) throws Exception {
        Optional<User> selectUser = userRepository.findById(userId);
        User updateUser;
        if (selectUser.isPresent()) {
            User user = selectUser.get();

            user.setName(name);
            user.setImage(image);
            user.setGreeting(greeting);

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

            if (password.equals(passwordEncoder.encrypt(user.getEmail(),oldPassword))){
                user.setPassword(passwordEncoder.encrypt(user.getEmail(), newPassword));
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
