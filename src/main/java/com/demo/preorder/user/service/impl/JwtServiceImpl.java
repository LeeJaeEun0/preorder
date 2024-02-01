package com.demo.preorder.user.service.impl;

import com.demo.preorder.user.dao.JwtDao;
import com.demo.preorder.user.dto.AccessTokenDto;
import com.demo.preorder.user.service.JwtService;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtDao jwtDao;

    public JwtServiceImpl(JwtDao jwtDao) {
        this.jwtDao = jwtDao;
    }

    @Override
    public void logout(String accessToekn) {
        jwtDao.logout(accessToekn);
    }
}
