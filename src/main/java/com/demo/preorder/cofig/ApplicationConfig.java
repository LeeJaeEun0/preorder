package com.demo.preorder.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ApplicationConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }
}
