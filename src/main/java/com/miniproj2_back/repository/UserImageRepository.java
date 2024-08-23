package com.miniproj2_back.repository;

import com.miniproj2_back.entity.UserImage;
//import com.miniproj2_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    Optional<UserImage> findByUser_Id(int userId);
}