package com.demo.preorder.profile.service;

import com.demo.preorder.profile.dto.ProfileDto;
import com.demo.preorder.profile.dto.ProfileResponseDto;
import com.demo.preorder.profile.entity.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {
    ProfileResponseDto saveProfile(Long userId, ProfileDto profileDto) throws IOException;

    ProfileResponseDto updateProfile(Long userId, ProfileDto profileDto) throws IOException;
}
