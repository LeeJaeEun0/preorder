package com.demo.preorder.profile.dao;

import com.demo.preorder.profile.entity.Profile;

public interface ProfileDao {

    Profile saveProfile(Profile profile);

    Profile updateProfile(Long userId, String image, String greeting);
}