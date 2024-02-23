package com.demo.preorder.user.service.impl;

import com.demo.preorder.user.dao.JwtDao;
import com.demo.preorder.user.dto.AccessTokenDto;
import com.demo.preorder.user.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtDao jwtDao;


    @Override
    public void logout(String accessToken) {
        jwtDao.logout(accessToken);
    }
}
