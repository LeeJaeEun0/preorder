package com.demo.preorder.member.repository;

import com.demo.preorder.member.entity.EmailCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailCertificationRepository extends JpaRepository<EmailCertification, Long> {
    Optional<EmailCertification> findByEmail(String email);
}
