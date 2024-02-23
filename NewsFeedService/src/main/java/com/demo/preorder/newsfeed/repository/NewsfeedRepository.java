package com.demo.preorder.newsfeed.repository;

import com.demo.preorder.newsfeed.entity.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    List<Newsfeed> findByUserIdOrderByCreatedDateDesc(Long userId);
}
