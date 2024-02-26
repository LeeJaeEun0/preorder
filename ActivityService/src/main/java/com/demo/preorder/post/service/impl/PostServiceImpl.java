package com.demo.preorder.post.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.NewsfeedServiceClient;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.PostResponseDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    private final NewsfeedServiceClient newsfeedServiceClient;

    private final FollowDao followDao;

    @Override
    public PostResponseDto savePost(Long userId, PostDto postDto) {
        Post post = new Post();
        post.setUserId(userId);
        post.setContents(postDto.getContents());
        Post saved = postDao.savePost(post);

        List<Follow> followList = followDao.findFollowing(saved.getUserId());

        if (followList != null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("post");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("PostServiceImpl - FollowingUserID = {} result = {} Timestamp = {}", follows.getUserId(), result, LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("PostServiceImpl - Error saving following for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        List<Follow> followList2 = followDao.findFollower(saved.getUserId());

        if (followList != null) {

            for (Follow follows : followList2) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getUserId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("post");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    ResponseEntity<String> stringResponseEntity = newsfeedServiceClient.saveNewsfeed(newsfeedClientDto);
                    String result = stringResponseEntity.getBody();
                    log.info("PostServiceImpl - FollowerUserID = {} result = {} Timestamp = {}", follows.getUserId(), result, LocalDateTime.now());
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("PostServiceImpl - Error saving follower for userID = {}: {}", follows.getUserId(), e.getMessage(), e);
                }
            }
        }

        return new PostResponseDto(saved);
    }

    @Override
    public PostResponseDto selectPost(Long postId) {
        return new PostResponseDto(postDao.selectPost(postId));
    }

    @Override
    public List<PostResponseDto> listPost() {
        List<Post> posts = postDao.listPost();
        List<PostResponseDto> postResponseDtos = posts.stream()
                .map(PostResponseDto::new) // Post 객체를 PostResponseDto로 변환하는 생성자 참조
                .collect(Collectors.toList());

        return postResponseDtos;
    }

    @Override
    public List<PostResponseDto> searchPost(SearchwordDto searchwordDto) {
        List<Post> posts = postDao.searchPost(searchwordDto.getSearchWord());

        List<PostResponseDto> postResponseDtos = posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return postResponseDtos;
    }

    @Override
    public PostResponseDto updatePost(Long userId, PostDto postDto) {
        return new PostResponseDto(postDao.updatePost(userId, postDto.getPostId(), postDto.getContents()));
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        postDao.deletePost(userId, postId);
    }
}
