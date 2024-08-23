package com.miniproj2_back.controller;

import com.miniproj2_back.responses.userImage.UserImageResponse;
import com.miniproj2_back.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/userimages")
public class UserImageController {
    @Autowired
    private UserImageService userImageService;

    public void UserImagesController(UserImageService userImageService) {
        this.userImageService = userImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UserImageResponse> upload(@RequestParam("image") MultipartFile file, @RequestParam int userId) throws IOException {
        UserImageResponse response = userImageService.upload(file,userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/download/{userId}")
    public ResponseEntity<byte[]> download(@PathVariable int userId){
        byte[] image = userImageService.download(userId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
