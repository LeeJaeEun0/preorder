package com.demo.preorder.newsfeed.repository;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsfeedIFollowRepository extends JpaRepository<NewsfeedFollowing, Long> {
    List<NewsfeedFollowing> findByUserIdIdOrderByCreatedDateDesc(Long userId);
}
