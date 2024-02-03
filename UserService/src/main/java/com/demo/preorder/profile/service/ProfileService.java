package com.demo.preorder.profile.service;

import com.demo.preorder.profile.dto.ProfileDto;
import com.demo.preorder.profile.entity.Profile;

public interface ProfileService {
    Profile saveProfile(Long userId, ProfileDto profileDto);

    Profile updateProfile(Long userId, ProfileDto profileDto);
}
