package com.demo.preorder.user.dao.impl;

import com.demo.preorder.user.dao.EmailCertificationDao;
import com.demo.preorder.user.entity.EmailCertification;
import com.demo.preorder.user.exception.CustomException;
import com.demo.preorder.user.exception.ErrorCode;
import com.demo.preorder.user.repository.EmailCertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailCertificationDaoImpl implements EmailCertificationDao {

    private final EmailCertificationRepository emailCertificationRepository;

    @Override
    public EmailCertification insertEmailCertification(EmailCertification emailCertification) {
        return emailCertificationRepository.save(emailCertification);
    }

    @Override
    public boolean emailCertification(String email, String number) {
        Pageable pageable = PageRequest.of(0, 1); // 첫 번째 페이지, 최대 1개의 결과
        List<EmailCertification> results = emailCertificationRepository.findLatestByEmail(email, pageable);
        EmailCertification latestEntity = null;
        if (!results.isEmpty()) {
            latestEntity = results.get(0); // 첫 번째 요소가 검색 조건에 맞는 가장 최근의 엔티티
            String emailAddress = latestEntity.getEmail();
            String certificationNumber = latestEntity.getNumber();
            if (emailAddress.equals(email) && certificationNumber.equals(number))
                return true;
            else throw new CustomException(ErrorCode.INVALID_NUMBER);
        }else{
            throw new CustomException(ErrorCode.INVALID_EMAIL_AUTHENTICATION);
        }
    }

}
