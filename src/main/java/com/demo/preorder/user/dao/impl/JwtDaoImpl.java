package com.demo.preorder.user.dao.impl;

import com.demo.preorder.user.dao.JwtDao;
import com.demo.preorder.user.entity.Jwt;
import com.demo.preorder.user.repository.JwtRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtDaoImpl implements JwtDao {

    private final JwtRepository jwtRepository;

    public JwtDaoImpl(JwtRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

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
