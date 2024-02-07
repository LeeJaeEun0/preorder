package com.demo.preorder.user.service.impl;

import com.demo.preorder.filter.VerifyUserFilter;
import com.demo.preorder.user.dto.UserLoginDto;
import com.demo.preorder.user.dto.UserRegisterDto;
import com.demo.preorder.user.dto.UserResponseDto;
import com.demo.preorder.user.dto.UserVerifyResponseDto;
import com.demo.preorder.user.entity.*;
import com.demo.preorder.user.provider.JwtProvider;
import com.demo.preorder.user.repository.UserRepository;
import com.demo.preorder.user.repository.UserRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper;


    @Transactional
    public UserResponseDto registerUser(UserRegisterDto userRegisterDto){
        User user = userRepository.save(userRegisterDto.toEntity());
        UserRole role = UserRole.builder()
                .role(Role.USER)
                .user(user)
                .build();
        if(user.getUserRoles() == null)
            user.setUserRoles(new HashSet<>());
        user.addRole(role);
        userRoleRepository.save(role);
        return new UserResponseDto(user);
    }

    public UserVerifyResponseDto verifyUser(UserLoginDto userLoginDto){
        Optional<User> optionalUser = userRepository.findByEmail(userLoginDto.getUserEmail());
        User user = optionalUser.get();
        log.info("info log userLoginDto = {}", userLoginDto.getUserPassword());
        log.info("info log user = {}", user.getPassword());
        if(user == null)
            return UserVerifyResponseDto.builder()
                    .isValid(false)
                    .build();
        return UserVerifyResponseDto.builder()
                .isValid(true)
                .userRole(user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toSet())).build();
    }

    public UserResponseDto findUserByEmail(String userEmail){
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        User user = optionalUser.get();
        return new UserResponseDto(user);
    }

    @Transactional
    public void updateRefreshToken(String userEmail,String refreshToken){
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        User user = optionalUser.get();
        if(user == null)
            return;
        user.updateRefreshToken(refreshToken);
    }

    @Transactional
    public Jwt refreshToken(String refreshToken){
        try{
            // 유효한 토큰 인지 검증
            jwtProvider.getClaims(refreshToken);
            User user = userRepository.findByRefreshToken(refreshToken);
            if(user == null)
                return null;

            HashMap<String, Object> claims = new HashMap<>();
            AuthenticateUser authenticateUser = new AuthenticateUser(user.getEmail(),
                    user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toSet()));
            String authenticateUserJson = objectMapper.writeValueAsString(authenticateUser);
            claims.put(VerifyUserFilter.AUTHENTICATE_USER,authenticateUserJson);
            Jwt jwt = jwtProvider.createJwt(claims);
            updateRefreshToken(user.getEmail(),jwt.getRefreshToken());
            return jwt;
        } catch (Exception e){
            return null;
        }
    }

    @Transactional
    public boolean addUserRole(String email, Role role){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();
        if(user.getUserRoles().stream().anyMatch(userRole -> userRole.getRole().equals(role)))
            return false;
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();
        user.addRole(userRole);
        userRoleRepository.save(userRole);
        return true;
    }

}