package com.demo.preorder.profile.dao.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.profile.dao.ProfileDao;
import com.demo.preorder.profile.entity.Profile;
import com.demo.preorder.profile.repository.ProfileRepository;
import com.demo.preorder.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileDaoImpl implements ProfileDao {

    private final ProfileRepository profileRepository;

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Long userId, String image, String greeting) {
        Profile profile = profileRepository.findByUserIdId(userId);
        if(profile== null) throw new CustomException(ErrorCode.INVALID_ID);

        profile.setGreeting(greeting);
        profile.setImage(image);

        return profileRepository.save(profile);
    }

    @Override
    public Profile findProfileByUserId(Long userId) {
        return profileRepository.findByUserIdId(userId);
    }
}
