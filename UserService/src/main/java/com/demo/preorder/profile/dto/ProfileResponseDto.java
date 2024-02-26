package com.demo.preorder.profile.dto;

import com.demo.preorder.profile.entity.Profile;
import lombok.Getter;

@Getter
public class ProfileResponseDto {
    private String email;
    private String greeting;
    private String image;

    public ProfileResponseDto(Profile profile) {
        this.email = profile.getUserId().getEmail();
        this.greeting = profile.getGreeting();
        this.image = profile.getImage();
    }
}
