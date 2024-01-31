package com.demo.preorder.profile.service.impl;

import com.demo.preorder.post.entity.Post;
import com.demo.preorder.profile.dao.ProfileDao;
import com.demo.preorder.profile.dto.ProfileDto;
import com.demo.preorder.profile.entity.Profile;
import com.demo.preorder.profile.service.ProfileService;
import com.demo.preorder.user.dao.UserDao;
import com.demo.preorder.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    private final UserDao userDao;

    public ProfileServiceImpl(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @Override
    public Profile saveProfile(Long userId, ProfileDto profileDto) {

        Profile profile = new Profile();
        User user = userDao.findUser(userId);
        if(user == null) return null;
        profile.setUserId(user);
        profile.setGreeting(profileDto.getGreeting());
        profile.setImage(profileDto.getImage());
        return profileDao.saveProfile(profile);
    }

    @Override
    public Profile updateProfile(Long userId, ProfileDto profileDto) {
        return profileDao.updateProfile(userId,profileDto.getImage(), profileDto.getGreeting());
    }
}
