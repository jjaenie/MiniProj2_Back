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
        PostImageResponse postImageResponse = postImageService.upload(file,postId);
        return new ResponseEntity<>(postImageResponse, HttpStatus.OK);
    }

    @GetMapping("/download/{postId}")
    public ResponseEntity<?> download(@PathVariable int postId) {
//        byte[] image = postImageService.download(postId);
//        if (image!=null){
//            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        try {
            String imagePath = postImageService.download(postId); // byte[]가 아닌 String으로 처리
            if (imagePath != null) {
                Path path = Paths.get(imagePath);
                String fileType = Files.probeContentType(path); // 파일 타입 결정
                byte[] image = Files.readAllBytes(path); // 파일을 읽어서 byte[]로 변환
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileType)).body(image);
            }
        } catch (IOException e) {
            e.printStackTrace(); // 로그에 예외 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading the image file");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}