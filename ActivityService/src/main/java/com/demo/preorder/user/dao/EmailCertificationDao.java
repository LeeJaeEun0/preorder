package com.demo.preorder.user.dao;

import com.demo.preorder.user.entity.EmailCertification;

public interface EmailCertificationDao {

    EmailCertification insertEmailCertification(EmailCertification emailCertification);
    boolean emailCertification(String email, String number);
}
