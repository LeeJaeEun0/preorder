package com.demo.preorder.profile.dao.impl;

import com.demo.preorder.profile.dao.ProfileDao;
import com.demo.preorder.profile.entity.Profile;
import com.demo.preorder.profile.repository.ProfileRepository;
import com.demo.preorder.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ProfileDaoImpl implements ProfileDao {

    private final ProfileRepository profileRepository;

    public ProfileDaoImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Long userId, String image, String greeting) {
        Profile profile = profileRepository.findByUserIdId(userId);
        if(profile== null) return null;

        profile.setGreeting(greeting);
        profile.setImage(image);

        return profileRepository.save(profile);
    }
}
