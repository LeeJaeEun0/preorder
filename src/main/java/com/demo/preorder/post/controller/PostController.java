package com.demo.preorder.post.controller;

import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> savePost(@PathVariable Long userId, @RequestBody PostDto postDto){
        Post post = postService.savePost(userId, postDto);
        if(post != null){
            return  ResponseEntity.status(HttpStatus.OK).body(post);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("포스트 작성에 실패했습니다.");
        }
    }

    @GetMapping("/selectPost")
    public ResponseEntity<?> selectPost(@RequestBody PostDto postDto){
        Post post = postService.selectPost(postDto);
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
    @PutMapping("/{userId}")
    public ResponseEntity<?> changePost(@PathVariable Long userId, @RequestBody PostDto postDto){
        Post post = postService.changePost(userId, postDto);
        if(post != null){
            return  ResponseEntity.status(HttpStatus.OK).body(post);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("포스트 수정에 실패했습니다.");
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Long userId, @RequestBody PostDto postDto) throws Exception {
        postService.deletePost(userId, postDto);
        return null;

    }

}
