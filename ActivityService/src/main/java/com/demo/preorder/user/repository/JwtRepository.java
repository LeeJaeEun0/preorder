package com.demo.preorder.user.repository;

import com.demo.preorder.user.entity.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtRepository extends JpaRepository<Jwt, Long> {
    Optional<Jwt> findByAccessToken(String accessToken);
}
