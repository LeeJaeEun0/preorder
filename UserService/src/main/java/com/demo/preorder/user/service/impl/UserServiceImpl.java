package com.demo.preorder.user.service.impl;

import com.demo.preorder.user.dao.EmailCertificationDao;
import com.demo.preorder.user.dao.UserDao;
import com.demo.preorder.user.dto.EmailDto;
import com.demo.preorder.user.dto.PasswordDto;
import com.demo.preorder.user.dto.ProfileDto;
import com.demo.preorder.user.entity.EmailCertification;
import com.demo.preorder.user.entity.User;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.user.jwt.JwtUtils;
import com.demo.preorder.user.provider.EmailProvider;
import com.demo.preorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final EmailCertificationDao emailCertificationDao;

    private  final EmailProvider emailProvider;
    @Override
    public boolean checkEmail(EmailDto emailDTO) {
        log.info("info log : emailService");
        String email = emailDTO.getEmail();
        boolean isExistEmail = userDao.checkEmail(email);
        if(isExistEmail) throw new CustomException(ErrorCode.EXISTS_EMAIL);

        log.info("info log = {}", isExistEmail);

        String certificationNumber = emailProvider.getCertificationNumber();
        boolean isSucceed = emailProvider.sendCertificationMail(email, certificationNumber);
        if(!isSucceed) throw new CustomException(ErrorCode.TRANSMISSION_FAILED);
        log.info("info log = {}", isSucceed);

        EmailCertification emailCertification = new EmailCertification();
        emailCertification.setEmail(emailDTO.getEmail());
        emailCertification.setNumber(certificationNumber);
        emailCertificationDao.insertEmailCertification(emailCertification);
        return true;
    }


    @Override
    public User getUser(Long userId) {
        return userDao.findUser(userId);
    }

    @Override
    public Long findUserId(Map<String, String> httpHeaders) {
        String jwtToken = httpHeaders.get("authorization");
        String email = JwtUtils.initJwtPayload(jwtToken);
        log.info("info log = {}",userDao.findUserId(email));
        return userDao.findUserId(email);
    }

    @Override
    public User changeUserProfile(Long userId, ProfileDto profileDto) {
        return userDao.updateUserProfile(userId,profileDto.getName());
    }

    @Override
    public User changeUserPassword(Long userId, PasswordDto passwordDto) {
        return userDao.updateUserPassword(userId, passwordDto.getOldPassword(), passwordDto.getNewPassword());
    }

    @Override
    public void deleteUser(Long userId){
        userDao.deleteUser(userId);
    }
}
