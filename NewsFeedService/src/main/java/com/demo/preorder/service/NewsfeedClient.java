package com.demo.preorder.service;

import com.demo.preorder.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
@Slf4j
@Service
public class NewsfeedClient {
    private final RestTemplate restTemplate;

    // RestTemplate을 생성자 주입 방식으로 의존성 주입
    @Autowired
    public NewsfeedClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Long findUserId(Map<String, String> httpHeaders){
        // URI 생성
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/internal/users/user")
                .encode()
                .build()
                .toUri();

        // HttpHeaders 객체 생성 및 맵에 있는 모든 헤더 추가
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(httpHeaders);

        // RequestEntity를 GET 요청으로 구성하고 HttpHeaders 객체 추가
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .headers(headers) // 헤더 추가
                .build();

        // exchange 메서드를 사용하여 요청 보내기 및 ResponseEntity<Long> 유형으로 응답 받기
        ResponseEntity<Long> responseEntity = restTemplate.exchange(requestEntity, Long.class); // 수정됨
        log.info("info log = {}",responseEntity);
        // 응답 본문 반환
        return responseEntity.getBody();
    }

    public User findUser(Long userId){
        // URI 생성 시 쿼리 파라미터로 userId 추가
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/internal/users/member")
                .queryParam("userId", userId) // 쿼리 파라미터로 userId 추가
                .encode()
                .build()
                .toUri();

        // RequestEntity를 GET 요청으로 구성
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();

        // exchange 메서드를 사용하여 요청 보내기
        ResponseEntity<User> responseEntity = restTemplate.exchange( // 수정됨
                requestEntity, User.class);
        log.info("info log = {}",responseEntity);
        // User 객체 반환
        return responseEntity.getBody();
    }
}
