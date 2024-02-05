package com.demo.preorder.user.repository;

import com.demo.preorder.user.entity.EmailCertification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface EmailCertificationRepository extends JpaRepository<EmailCertification, Long> {
    Optional<EmailCertification> findByEmail(String email);

    @Query("SELECT e FROM EmailCertification e WHERE e.email = :email ORDER BY e.number DESC")
    List<EmailCertification> findLatestByEmail(@Param("email") String email, Pageable pageable);
}
