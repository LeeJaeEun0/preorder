package com.demo.preorder.filter;

import com.demo.preorder.user.dto.UserLoginDto;
import com.demo.preorder.user.dto.UserVerifyResponseDto;
import com.demo.preorder.user.entity.AuthenticateUser;
import com.demo.preorder.user.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class VerifyUserFilter implements Filter {
    public static final String AUTHENTICATE_USER = "authenticateUser";
    private final ObjectMapper objectMapper;

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if ((httpServletRequest.getMethod().equals("POST"))) {
            try {
                UserLoginDto userLoginDto = objectMapper.readValue(request.getReader(), UserLoginDto.class);
                UserVerifyResponseDto verifyResponse = userService.verifyUser(userLoginDto);
                if (verifyResponse.isValid()) {
                    request.setAttribute(AUTHENTICATE_USER, new AuthenticateUser(userLoginDto.getUserEmail(),verifyResponse.getUserRole()));
                } else
                    throw new IllegalArgumentException();
                chain.doFilter(request, response);
            } catch (Exception e) {
                log.error("Fail User Verify");
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(),e.getMessage());
            }
        }
    }
}