package com.miniproj2_back.controller;

import com.miniproj2_back.responses.post.PostImageResponse;
import com.miniproj2_back.service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/postimages")
public class PostImageController {

    @Autowired
    private PostImageService postImageService;

    public PostImageController(PostImageService postImageService) {
        this.postImageService = postImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<PostImageResponse> upload(@RequestParam("image") MultipartFile file, @RequestParam int postId) throws IOException {
//        PostImageResponse postImageResponse = postImageService.upload(file,postId);
//        return new ResponseEntity<>(postImageResponse, HttpStatus.OK);

        PostImageResponse postImageResponse = null;
        if (file != null && !file.isEmpty()) {
            postImageResponse = postImageService.upload(file, postId);
        }

        // 이미지가 없을 경우에도 정상적으로 처리
        return new ResponseEntity<>(postImageResponse, HttpStatus.OK);
    }

    @GetMapping("/download/{postId}")
    public ResponseEntity<?> download(@PathVariable int postId) {
        try {
            String imagePath = postImageService.download(postId);
            if (imagePath != null) {
                Path path = Paths.get(imagePath);
                String fileType = Files.probeContentType(path);
                byte[] image = Files.readAllBytes(path);
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileType)).body(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading the image file");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}