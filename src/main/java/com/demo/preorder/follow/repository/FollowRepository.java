package com.demo.preorder.follow.repository;

import com.demo.preorder.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT f FROM Follow f WHERE f.userId.id = :userId AND f.followingId.id = :followingId")
    Optional<Follow> findFollow(@Param("userId") Long userId, @Param("followingId") Long followingId);

    // 내가 팔로우한 사람
    @Query("SELECT f FROM Follow f WHERE f.userId.id = :userId")
    Optional<List<Follow>> peopleIfollow(@Param("userId")  Long userId);

    // 나를 팔로우한 사람
    @Query("SELECT f FROM Follow f WHERE f.followingId.id = :followingId")
    Optional<List<Follow>> whofollowedMe(@Param("followingId") Long followingId);

}
