package com.demo.preorder.post.service.impl;

import com.demo.preorder.client.dto.NewsfeedClientDto;
import com.demo.preorder.client.service.ActivityClient;
import com.demo.preorder.follow.dao.FollowDao;
import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.post.dao.PostDao;
import com.demo.preorder.post.dto.PostDto;
import com.demo.preorder.post.dto.SearchwordDto;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    private final ActivityClient activityClient;

    private final FollowDao followDao;

    @Override
    public Post savePost(Long userId,PostDto postDto) {
        Post post = new Post();
        if(activityClient.findUser(userId) == null)
            return null;

        post.setUserId(activityClient.findUser(userId));
        post.setContents(postDto.getContents());
        Post saved = postDao.savePost(post);

        // 내가 팔로우한 사람 찾아서 newsfeed
        List<Follow> followList = followDao.findFollowing(saved.getUserId().getId());

        if (followList!= null) {

            for (Follow follows : followList) {
                NewsfeedClientDto newsfeedClientDto = new NewsfeedClientDto();
                newsfeedClientDto.setUserId(follows.getFollowingId());
                newsfeedClientDto.setSenderId(saved.getUserId());
                newsfeedClientDto.setType("post");
                newsfeedClientDto.setTargetId(saved.getId());

                try {
                    // 외부 서비스 호출
                    String result = activityClient.saveNewsfeed(newsfeedClientDto);
                    log.info("Info log: Following - userID={} result={}", follows.getUserId(), result);
                } catch (Exception e) {
                    // 오류 발생 시 처리
                    log.error("Error saving following for userID={}: {}", follows.getUserId(), e.getMessage(), e);
                    // 필요한 경우, 여기서 추가적인 오류 처리 로직을 구현할 수 있습니다.
                }
            }
        }

        return saved;
    }

    @Override
    public Post selectPost(PostDto postDto) {
        return postDao.selectPost(postDto.getPostId());
    }

    @Override
    public List<Post> listPost() {
        return postDao.listPost();
    }

    @Override
    public List<Post> searchPost(SearchwordDto searchwordDto) {
        return postDao.searchPost(searchwordDto.getSearchWord());
    }

    @Override
    public Post changePost(Long userId,PostDto postDto) {
        return postDao.changePost(userId, postDto.getPostId(),postDto.getContents());
    }

    @Override
    public void deletePost(Long userId,PostDto postDto) throws Exception {
        postDao.deletePost(userId,postDto.getPostId());
    }
}
