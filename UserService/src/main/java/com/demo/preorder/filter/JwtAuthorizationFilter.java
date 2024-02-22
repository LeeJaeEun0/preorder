package com.demo.preorder.filter;
import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.user.entity.AuthenticateUser;
import com.demo.preorder.user.entity.Role;
import com.demo.preorder.user.exception.AuthorizationException;
import com.demo.preorder.user.provider.JwtProvider;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements Filter {
    private final String[] whiteListUris = new String[]{"/user/login","/auth/refresh/token","/user/register","*/h2-console*","/api/**"};

    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if(whiteListCheck(httpServletRequest.getRequestURI())){
            chain.doFilter(request, response);
            return;
        }
        if(!isContainToken(httpServletRequest)){
            throw new CustomException(ErrorCode.JWT_EXCEPTION);
        }
        try{
            String token = getToken(httpServletRequest);
            AuthenticateUser authenticateUser = getAuthenticateUser(token);
            verifyAuthorization(httpServletRequest.getRequestURI(),authenticateUser);
            log.info("값 : {}",authenticateUser.getEmail());
            chain.doFilter(request, response);
        } catch (JsonParseException e){
            throw new CustomException(ErrorCode.JSON_PARSE_EXCEPTION);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException e){
            throw new CustomException(ErrorCode.JWT_EXCEPTION);
        } catch (ExpiredJwtException e){
            throw new CustomException(ErrorCode.JWT_TOKEN_EXPIRED);
        } catch (AuthorizationException e){
            throw new CustomException(ErrorCode.AUTHORIZATION_EXCEPTION);
        }
    }

    private boolean whiteListCheck(String uri){
        return PatternMatchUtils.simpleMatch(whiteListUris,uri);
    }

    private boolean isContainToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return authorization != null && authorization.startsWith("Bearer ");
    }

    private String getToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return authorization.substring(7);
    }

    private AuthenticateUser getAuthenticateUser(String token) throws JsonProcessingException {
        Claims claims = jwtProvider.getClaims(token);
        String authenticateUserJson = claims.get(VerifyUserFilter.AUTHENTICATE_USER, String.class);
        return objectMapper.readValue(authenticateUserJson, AuthenticateUser.class);
    }

    private void verifyAuthorization(String uri, AuthenticateUser user){
        if(PatternMatchUtils.simpleMatch("*/admin*",uri) && !user.getRoles().contains(Role.ADMIN)){
            throw new AuthorizationException();
        }
    }
}