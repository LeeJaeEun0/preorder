package com.demo.preorder.user.jwt;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtPayload {
    private String userName;
    private String email;
    private List<String> scope;
    private Long exp;
    private List<String> authorities;
    private String jti;
    private String clientId;

    /**
     * custom values
     */
    private String name;
    private String customValue;
}
