package com.demo.preorder.member.service.impl;

import com.demo.preorder.member.entity.EmailCertification;
import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.model.EmailCertificationDao;
import com.demo.preorder.member.model.UserDao;
import com.demo.preorder.member.model.UserDto;
import com.demo.preorder.member.provider.EmailProvider;
import com.demo.preorder.member.repository.UserRepository;
import com.demo.preorder.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailCertificationDao emailCertificationDao;

    private  final EmailProvider emailProvider;
    @Override
    public boolean checkEmail(UserDto userDto) {
        String email = userDto.getEmail();

        boolean isExistEmail = userDao.checkEmail(email);
        if(isExistEmail) return false;

        String certificationNumber = emailProvider.getCertificationNumber();
        boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
        if(!isSuccessed) return false;

        EmailCertification emailCertification = new EmailCertification();
        emailCertification.setEmail(userDto.getEmail());
        emailCertification.setNumber(certificationNumber);
        emailCertificationDao.insertEmailCertification(emailCertification);
        return true;
    }

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
