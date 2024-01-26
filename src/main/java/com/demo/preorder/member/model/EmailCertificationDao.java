package com.demo.preorder.member.model;

import com.demo.preorder.member.entity.EmailCertification;

public interface EmailCertificationDao {

    EmailCertification insertEmailCertification(EmailCertification emailCertification);
    boolean emailCertification(String email, String number);
}
