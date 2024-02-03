package com.demo.preorder.profile.repository;

import com.demo.preorder.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUserIdId(Long userId);
}
