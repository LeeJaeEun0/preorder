package com.demo.preorder.newsfeed.repository;

import com.demo.preorder.newsfeed.entity.NewsfeedIFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsfeedIFollowRepository extends JpaRepository<NewsfeedIFollow, Long> {
    List<NewsfeedIFollow> findByUserIdIdOrderByIdDesc(Long userId);
}
