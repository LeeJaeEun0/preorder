package com.demo.preorder.user.dao.impl;

import com.demo.preorder.user.dao.JwtDao;
import com.demo.preorder.user.entity.Jwt;
import com.demo.preorder.user.repository.JwtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtDaoImpl implements JwtDao {

    private final JwtRepository jwtRepository;

    @Override
    public void logout(String accessToken) {
        Optional<Jwt> optionalJwt = jwtRepository.findByAccessToken(accessToken);
        if(optionalJwt.isPresent()){
            Jwt jwt = optionalJwt.get();

            if(jwt.getAccessToken().equals(accessToken))
                jwtRepository.delete(jwt);
        }
    }
}
