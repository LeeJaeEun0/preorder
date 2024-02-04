package com.demo.preorder.user.service;

import com.demo.preorder.user.dto.EmailCertificationDto;

public interface EmailCertificationService {
    boolean emailCertification(EmailCertificationDto emailCertificationDto);
}
