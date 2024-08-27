package com.miniproj2_back.repository;

import com.miniproj2_back.entity.PostImage;
import com.miniproj2_back.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostImageRepository extends JpaRepository<PostImage, Integer> {
    Optional<PostImage> findPostImageByPost_Id(int postId);

    // 특정 Post ID에 이미지가 존재하는지 확인하는 메서드 추가
    boolean existsByPost_Id(int postId);
}