package com.demo.preorder.profile.service.impl;

import com.demo.preorder.profile.dao.ProfileDao;
import com.demo.preorder.profile.dto.ProfileDto;
import com.demo.preorder.profile.entity.Profile;
import com.demo.preorder.profile.service.ProfileService;
import com.demo.preorder.user.dao.UserDao;
import com.demo.preorder.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    private final UserDao userDao;

    @Override
    public Profile saveProfile(Long userId, ProfileDto profileDto) throws IOException {

        try {
            Profile profile = new Profile();
            User user = userDao.findUser(userId);
            if (user == null) return null;
            profile.setUserId(user);
            profile.setGreeting(profileDto.getGreeting());
            if(profileDto.getFile() == null) return null;
            profile.setImage(userId.toString() + profileDto.getFile().getOriginalFilename());

            // 파일 저장
            String uploadDir = "C:/path/to/upload/dir/";
            Path path = Paths.get(uploadDir + userId.toString() + profileDto.getFile().getOriginalFilename());
            Files.copy(profileDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return profileDao.saveProfile(profile);
        } catch (IOException e) {
            log.error("파일 업로드 및 저장 실패: {}", profileDto.getFile().getOriginalFilename(), e);
        }

        return null;
    }

    @Override
    public Profile updateProfile(Long userId, ProfileDto profileDto) throws IOException {

        try {
            Profile profile = profileDao.findProfileByUserId(userId) ;
            String fileName = profile.getImage();

            if(!deleteFile(fileName)) return null;

            User user = userDao.findUser(userId);
            if (user == null) return null;
            if(profileDto.getFile() == null) return null;


            String uploadDir = "C:/path/to/upload/dir/";
            Path path = Paths.get(uploadDir +userId.toString() + profileDto.getFile().getOriginalFilename());
            Files.copy(profileDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("file upload");
            return profileDao.updateProfile(userId, userId.toString()+profileDto.getFile().getOriginalFilename(), profileDto.getGreeting());
        } catch (IOException e) {
            log.error("파일 업로드 및 저장 실패: {}", profileDto.getFile().getOriginalFilename(), e);
        }

        return null;
    }

    public boolean deleteFile(String fileName) {
        Path fileStorageLocation = Paths.get("C:/path/to/upload/dir/").toAbsolutePath().normalize();
        Path filePath = fileStorageLocation.resolve(fileName).normalize();

        try {
            Files.deleteIfExists(filePath);
            return true;
        } catch (IOException ex) {
            // 예외 처리
            return false;
        }
    }
}
