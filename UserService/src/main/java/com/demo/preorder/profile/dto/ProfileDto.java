package com.demo.preorder.profile.dto;

import com.demo.preorder.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProfileDto {
    private MultipartFile file;

    private String greeting;
}
