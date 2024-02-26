package com.demo.preorder.user.service.impl;

import com.demo.preorder.cofig.PasswordEncoder;
import com.demo.preorder.filter.VerifyUserFilter;
import com.demo.preorder.user.dto.UserLoginDto;
import com.demo.preorder.user.dto.UserRegisterDto;
import com.demo.preorder.user.dto.UserResponseDto;
import com.demo.preorder.user.dto.UserVerifyResponseDto;
import com.demo.preorder.user.entity.*;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.user.provider.JwtProvider;
import com.demo.preorder.user.repository.UserRepository;
import com.demo.preorder.user.repository.UserRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto registerUser(UserRegisterDto userRegisterDto) {

        Optional<User> savedUser = userRepository.findByEmail(userRegisterDto.getUserEmail());
        if (savedUser.isPresent()) {
            throw new CustomException(ErrorCode.EXISTS_EMAIL);
        }

        String encryptedPassword = passwordEncoder.encrypt(userRegisterDto.getUserEmail(), userRegisterDto.getPassword());

        User user = User.builder()
                .name(userRegisterDto.getUsername())
                .email(userRegisterDto.getUserEmail())
                .password(encryptedPassword)
                .build();

        User saveduser = userRepository.save(user);
        UserRole role = UserRole.builder()
                .role(Role.USER)
                .user(saveduser)
                .build();
        if (saveduser.getUserRoles() == null)
            saveduser.setUserRoles(new HashSet<>());
        saveduser.addRole(role);
        userRoleRepository.save(role);
        return new UserResponseDto(saveduser);
    }

    public UserVerifyResponseDto verifyUser(UserLoginDto userLoginDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userLoginDto.getUserEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (!user.getPassword().equals(passwordEncoder.encrypt(userLoginDto.getUserEmail(), userLoginDto.getUserPassword())))
                throw new CustomException(ErrorCode.INVALID_PASSWORD);
            return UserVerifyResponseDto.builder()
                    .isValid(true)
                    .userRole(user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toSet())).build();
        } else {
            throw new CustomException(ErrorCode.INVALID_EMAIL);
        }
    }

    public UserResponseDto findUserByEmail(String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        User user = optionalUser.get();
        return new UserResponseDto(user);
    }

    @Transactional
    public void updateRefreshToken(String userEmail, String refreshToken) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        User user = optionalUser.get();
        if (user == null)
            return;
        user.updateRefreshToken(refreshToken);
    }

    @Transactional
    public Jwt refreshToken(String refreshToken) {
        try {
            // 유효한 토큰 인지 검증
            jwtProvider.getClaims(refreshToken);
            User user = userRepository.findByRefreshToken(refreshToken);
            if (user == null)
                return null;

            HashMap<String, Object> claims = new HashMap<>();
            AuthenticateUser authenticateUser = new AuthenticateUser(user.getEmail(),
                    user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toSet()));
            String authenticateUserJson = objectMapper.writeValueAsString(authenticateUser);
            claims.put(VerifyUserFilter.AUTHENTICATE_USER, authenticateUserJson);
            Jwt jwt = jwtProvider.createJwt(claims);
            updateRefreshToken(user.getEmail(), jwt.getRefreshToken());
            return jwt;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean addUserRole(String email, Role role) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();
        if (user.getUserRoles().stream().anyMatch(userRole -> userRole.getRole().equals(role)))
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