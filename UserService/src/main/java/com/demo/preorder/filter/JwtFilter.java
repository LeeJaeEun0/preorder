package com.demo.preorder.filter;

import com.demo.preorder.user.entity.AuthenticateUser;
import com.demo.preorder.user.entity.Jwt;
import com.demo.preorder.user.provider.JwtProvider;
import com.demo.preorder.user.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper;

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        Object attribute = request.getAttribute(VerifyUserFilter.AUTHENTICATE_USER);
        if (attribute instanceof AuthenticateUser authenticateUser) {
            Map<String, Object> claims = new HashMap<>();
            String authenticateUserJson = objectMapper.writeValueAsString(authenticateUser);
            claims.put(VerifyUserFilter.AUTHENTICATE_USER, authenticateUserJson);
            Jwt jwt = jwtProvider.createJwt(claims);
            userService.updateRefreshToken(authenticateUser.getEmail(), jwt.getRefreshToken());
            String json = objectMapper.writeValueAsString(jwt);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
    }
}