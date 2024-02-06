package com.demo.preorder.newsfeed.repository;

import com.demo.preorder.newsfeed.entity.NewsfeedFollower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedFollowedMeRepository extends JpaRepository<NewsfeedFollower, Long> {
    List<NewsfeedFollower> findByUserIdIdOrderByCreatedDateDesc(Long userId);
}
