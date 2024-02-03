package com.demo.preorder.user.service;

import com.demo.preorder.user.dto.AccessTokenDto;

public interface JwtService {
    void logout(String accessToekn);
}
