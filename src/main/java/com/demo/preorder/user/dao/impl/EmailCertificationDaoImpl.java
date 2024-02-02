package com.demo.preorder.user.dao.impl;

import com.demo.preorder.user.dao.EmailCertificationDao;
import com.demo.preorder.user.entity.EmailCertification;
import com.demo.preorder.user.repository.EmailCertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailCertificationDaoImpl implements EmailCertificationDao {

    private final EmailCertificationRepository emailCertificationRepository;

    public EmailCertificationDaoImpl(EmailCertificationRepository emailCertificationRepository) {
        this.emailCertificationRepository = emailCertificationRepository;
    }


    @Override
    public EmailCertification insertEmailCertification(EmailCertification emailCertification) {
        return emailCertificationRepository.save(emailCertification);
    }

    @Override
    public boolean emailCertification(String email, String number) {
        Optional<EmailCertification> emailCertification = emailCertificationRepository.findByEmail(email);
        if(emailCertification.isPresent())
        {
            EmailCertification certification = emailCertification.get();
            String emailAddress = certification.getEmail();
            String certificationNumber = certification.getNumber();
            if (emailAddress.equals(email) && certificationNumber.equals(number)) return true;
            else return false;
        }else{
            return false;
        }
    }

}
