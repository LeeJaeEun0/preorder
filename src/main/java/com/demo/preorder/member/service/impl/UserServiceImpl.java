package com.demo.preorder.member.service.impl;

import com.demo.preorder.cofig.PasswordEncoder;
import com.demo.preorder.member.entity.EmailCertification;
import com.demo.preorder.member.entity.User;
import com.demo.preorder.member.model.*;
import com.demo.preorder.member.provider.EmailProvider;
import com.demo.preorder.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    private EmailCertificationDao emailCertificationDao;

    private  final EmailProvider emailProvider;
    @Override
    public boolean checkEmail(EmailDto emailDTO) {
        String email = emailDTO.getEmail();

        boolean isExistEmail = userDao.checkEmail(email);
        if(isExistEmail) return false;

        String certificationNumber = emailProvider.getCertificationNumber();
        boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
        if(!isSuccessed) return false;

        EmailCertification emailCertification = new EmailCertification();
        emailCertification.setEmail(emailDTO.getEmail());
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
        user.setPassword(passwordEncoder.encrypt(userDto.getEmail(),userDto.getPassword()));
        user.setName(userDto.getName());
        user.setImage(userDto.getImage());
        user.setGreeting(user.getGreeting());
        return userDao.insertUser(user);
    }

    @Override
    public User changeUserProfile(Long userId, ProfileDto profileDto) throws Exception {
        return userDao.updateUserProfile(userId,profileDto.getName(), profileDto.getImage(), profileDto.getGreeting());
    }

    @Override
    public User changeUserPassword(Long userId, PasswordDto passwordDto) throws Exception {
        return userDao.updateUserPassword(userId, passwordDto.getOldPassword(), passwordDto.getNewPassword());
    }

    @Override
    public void deleteUser(Long userId) throws Exception {

    }
}
