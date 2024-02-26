package com.demo.preorder.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class JwtUtils {
    public static String initJwtPayload(String bearerJwt) {

        bearerJwt = StringUtils.removeStart(bearerJwt, "Bearer ");

        try {
            DecodedJWT jwt = JWT.decode(bearerJwt);
            Map<String, Claim> claims = jwt.getClaims();
            System.out.println(claims);
            Claim claim = claims.get("authenticateUser");
            String authenticateUserJson = claim.asString();

            // JSON 문자열을 객체로 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(authenticateUserJson);

            // email 값 추출
            String email = rootNode.get("email").asText();
            System.out.println("Email: " + email);

            return email;

        } catch (Exception e) {
            //log.error("build Jwt Error: {}", e.getCause().getMessage());
            return null;
        }
    }
}
