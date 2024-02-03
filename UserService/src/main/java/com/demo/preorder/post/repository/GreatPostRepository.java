package com.demo.preorder.post.repository;

import com.demo.preorder.post.entity.GreatPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreatPostRepository extends JpaRepository<GreatPost, Long> {
    List<GreatPost> findByPostIdId(Long postId);
}
