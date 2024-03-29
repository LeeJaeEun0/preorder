package com.demo.preorder.user.service.impl;

import com.demo.preorder.user.dao.EmailCertificationDao;
import com.demo.preorder.user.dao.UserDao;
import com.demo.preorder.user.dto.EmailDto;
import com.demo.preorder.user.dto.PasswordDto;
import com.demo.preorder.user.dto.ProfileDto;
import com.demo.preorder.user.dto.UserResponseDto;
import com.demo.preorder.user.entity.EmailCertification;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.user.jwt.JwtUtils;
import com.demo.preorder.user.provider.EmailProvider;
import com.demo.preorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final EmailCertificationDao emailCertificationDao;

    private final EmailProvider emailProvider;

    @Override
    public boolean checkEmail(EmailDto emailDTO) {
        log.info("info log : UserServiceImpl");
        String email = emailDTO.getEmail();
        boolean isExistEmail = userDao.checkEmail(email);
        if (isExistEmail) throw new CustomException(ErrorCode.EXISTS_EMAIL);

        log.info("UserServiceImpl - isExistEmail= {} , Timestamp: {}", isExistEmail, LocalDateTime.now());

        String certificationNumber = emailProvider.getCertificationNumber();
        boolean isSucceed = emailProvider.sendCertificationMail(email, certificationNumber);
        if (!isSucceed) throw new CustomException(ErrorCode.TRANSMISSION_FAILED);
        log.info("UserServiceImpl - isSucceed = {}, Timestamp: {}", isSucceed,LocalDateTime.now());

        EmailCertification emailCertification = new EmailCertification();
        emailCertification.setEmail(emailDTO.getEmail());
        emailCertification.setNumber(certificationNumber);
        emailCertificationDao.insertEmailCertification(emailCertification);
        return true;
    }


    @Override
    public UserResponseDto getUser(Long userId) {
        return new UserResponseDto(userDao.findUser(userId));
    }

    @Override
    public Long findUserId(Map<String, String> httpHeaders) {
        String jwtToken = httpHeaders.get("authorization");
        String email = JwtUtils.initJwtPayload(jwtToken);
        return userDao.findUserId(email);
    }

    @Override
    public UserResponseDto changeUserProfile(Long userId, ProfileDto profileDto) {
        return new UserResponseDto(userDao.updateUserProfile(userId, profileDto.getName()));
    }

    @Override
    public UserResponseDto changeUserPassword(Long userId, PasswordDto passwordDto) {
        return new UserResponseDto(userDao.updateUserPassword(userId, passwordDto.getOldPassword(), passwordDto.getNewPassword()));
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }
}
