package com.demo.preorder.newsfeed.repository;

import com.demo.preorder.newsfeed.entity.NewsfeedMyNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedMyNewsRepository extends JpaRepository<NewsfeedMyNews, Long> {
    List<NewsfeedMyNews> findByUserIdIdOrderByCreatedDateDesc(Long userId);
}
