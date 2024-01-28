package com.demo.preorder.post.repository;

import com.demo.preorder.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.contents like CONCAT('%', :text, '%')")
    List<Post> findContents(@Param("text") String text);
}
