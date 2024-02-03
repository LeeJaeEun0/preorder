package com.demo.preorder.newsfeed.repository;

import com.demo.preorder.newsfeed.entity.NewsfeedFollowedMe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedFollowedMeRepository extends JpaRepository<NewsfeedFollowedMe, Long> {
    List<NewsfeedFollowedMe> findByUserIdIdOrderByCreatedDateDesc(Long userId);
}
