package com.demo.preorder.user.service.impl;

import com.demo.preorder.user.dao.EmailCertificationDao;
import com.demo.preorder.user.dto.EmailCertificationDto;
import com.demo.preorder.user.dto.EmailDto;
import com.demo.preorder.user.repository.EmailCertificationRepository;
import com.demo.preorder.user.service.EmailCertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailCertificationServiceImpl implements EmailCertificationService {

    private final EmailCertificationDao emailCertificationDao;

    @Override
    public boolean emailCertification(EmailCertificationDto emailCertificationDto) {
        return emailCertificationDao.emailCertification(emailCertificationDto.getEmail(), emailCertificationDto.getNumber());
    }
}
