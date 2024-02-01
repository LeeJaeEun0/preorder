package com.demo.preorder.post.dao.impl;

import com.demo.preorder.follow.entity.Follow;
import com.demo.preorder.follow.repository.FollowRepository;
import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import com.demo.preorder.newsfeed.repository.NewsfeedMyNewsRepository;
import com.demo.preorder.user.entity.User;
import com.demo.preorder.user.repository.UserRepository;
import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import com.demo.preorder.newsfeed.repository.NewsfeedIFollowRepository;
import com.demo.preorder.post.dao.GreatPostDao;
import com.demo.preorder.post.entity.GreatPost;
import com.demo.preorder.post.entity.Post;
import com.demo.preorder.post.repository.GreatPostRepository;
import com.demo.preorder.post.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GreatPostDaoImpl implements GreatPostDao {

    private final GreatPostRepository greatPostRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final FollowRepository followRepository;

    private final NewsfeedIFollowRepository newsfeedIFollowRepository;

    private final NewsfeedMyNewsRepository newsfeedMyNewsRepository;
    public GreatPostDaoImpl(GreatPostRepository greatPostRepository, UserRepository userRepository, PostRepository postRepository, FollowRepository followRepository, NewsfeedIFollowRepository newsfeedIFollowRepository, NewsfeedMyNewsRepository newsfeedMyNewsRepository) {
        this.greatPostRepository = greatPostRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.followRepository = followRepository;
        this.newsfeedIFollowRepository = newsfeedIFollowRepository;
        this.newsfeedMyNewsRepository = newsfeedMyNewsRepository;
    }

    @Override
    public GreatPost saveGreatPost(Long userId, Long postId) {
        GreatPost greatPost = new GreatPost();
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalUser==null || optionalPost==null) return null;
        User user = optionalUser.get();
        Post post = optionalPost.get();

        greatPost.setUserId(user);
        greatPost.setPostId(post);
        GreatPost saved = greatPostRepository.save(greatPost);
        Optional<List<Follow>>optionalFollowList = followRepository.findByFollowingIdId(saved.getUserId().getId());

        if (optionalFollowList.isPresent()) {
            List<Follow> followList = optionalFollowList.get();

            for (Follow follows : followList) {
                NewsfeedIFollow newsfeedIFollow = new NewsfeedIFollow();
                newsfeedIFollow.setUserId(follows.getUserId());
                newsfeedIFollow.setFollowingId(saved.getUserId());
                newsfeedIFollow.setType("greatPost");
                newsfeedIFollow.setTargetId(saved.getId());
                newsfeedIFollowRepository.save(newsfeedIFollow);
            }
        }

        // 포스트를 작성한 사람에게 좋아요 알림
        NewsfeedMyNews newsfeedFollowedMe = new NewsfeedMyNews();
        newsfeedFollowedMe.setUserId(post.getUserId());
        newsfeedFollowedMe.setWriterId(saved.getUserId());
        newsfeedFollowedMe.setType("great");
        newsfeedFollowedMe.setPostId(post.getId());
        newsfeedMyNewsRepository.save(newsfeedFollowedMe);

        return saved;

    }

    @Override
    public void deleteGreatPost(Long userId, Long greatPostId) {
        Optional<GreatPost> optionalGreatPost = greatPostRepository.findById(greatPostId);
        if(optionalGreatPost.isPresent()){
            GreatPost greatPost = optionalGreatPost.get();
            if(greatPost.getUserId().getId().equals(userId))
                greatPostRepository.delete(greatPost);
        }

    }

    @Override
    public List<GreatPost> greatPostList(Long postId) {
        return greatPostRepository.findByPostIdId(postId);

    }
}
