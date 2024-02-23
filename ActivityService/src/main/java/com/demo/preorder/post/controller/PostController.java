package com.demo.preorder.post.controller;

import com.demo.preorder.client.service.UserServiceClient;
import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    
    private final PostService postService;

    private final UserServiceClient userServiceClient;

    @PostMapping
    public ResponseEntity<?> savePost(@RequestHeader Map<String, String> httpHeaders,
                                      @RequestBody PostDto postDto){
        log.info("info log = {}",httpHeaders);
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        Post post = postService.savePost(userId, postDto);

        if(post != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(post);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("포스트 작성에 실패했습니다.");
        }
    }

    @GetMapping("/selectPost")
    public ResponseEntity<?> selectPost(@RequestParam Long postId){
        Post post = postService.selectPost(postId);
        if(post != null){
            return  ResponseEntity.status(HttpStatus.OK).body(post);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("포스트 찾기 실패 했습니다.");
        }
    }
    @GetMapping("/listPost")
    public ResponseEntity<?> listPost(){
        List<Post> postList = postService.listPost();
        return  ResponseEntity.status(HttpStatus.OK).body(postList);

    }
    @GetMapping("/searchPost")
    public ResponseEntity<?> searchPost(@RequestBody SearchwordDto searchwordDto){
        List<Post> postList = postService.searchPost(searchwordDto);
        return  ResponseEntity.status(HttpStatus.OK).body(postList);

    }
    @PutMapping
    public ResponseEntity<?> changePost(@RequestHeader Map<String, String> httpHeaders,
                                        @RequestBody PostDto postDto){

        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        log.info("info log = {}",userId);
        log.info("info log = {}",postDto.getPostId());
        log.info("info log = {}",postDto.getContents());

        Post post = postService.changePost(userId, postDto);
        if(post != null){
            return  ResponseEntity.status(HttpStatus.OK).body(post);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("포스트 수정에 실패했습니다.");
        }
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@RequestHeader Map<String, String> httpHeaders,
                                        @PathVariable Long postId) throws Exception {
        ResponseEntity<Long> responseEntity= userServiceClient.findUserId(httpHeaders);
        Long userId = responseEntity.getBody();
        postService.deletePost(userId, postId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");

    }

}
