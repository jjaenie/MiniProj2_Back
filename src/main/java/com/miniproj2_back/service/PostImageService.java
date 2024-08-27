package com.miniproj2_back.service;

import com.miniproj2_back.entity.PostImage;
import com.miniproj2_back.mappers.PostImageMapper;
import com.miniproj2_back.repository.PostImageRepository;
import com.miniproj2_back.responses.post.PostImageResponse;
import com.miniproj2_back.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostImageService {

    @Autowired
    private PostImageRepository postImageRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private PostImageMapper postImageMapper;

    public PostImageService(PostImageRepository postImageRepository, PostService postService, PostImageMapper postImageMapper) {
        this.postImageRepository = postImageRepository;
        this.postService = postService;
        this.postImageMapper = postImageMapper;
    }

//    public PostImageResponse upload(MultipartFile file, int postId) throws IOException {
//        PostImage postImage = new PostImage();
//        postImage.setName(file.getOriginalFilename());
//        postImage.setType(file.getContentType());
//        postImage.setData(ImageUtil.compressImage(file.getBytes()));
//        postImage.setPost(postService.getById(postId));
//        postImageRepository.save(postImage);
//        return postImageMapper.imageToResponse(postImage);
//    }
//
//    public byte[] download(int id){
//        Optional<PostImage> postImage = postImageRepository.findPostImageByPost_Id(id);
//        if (postImage.isPresent()){
//            return ImageUtil.decompressImage(postImage.get().getData());
//        }
//        return null;
//    }

    public PostImageResponse upload(MultipartFile file, int postId) throws IOException {
//        // 파일을 저장할 경로 생성
//        String uploadDir = "/path/to/uploads/";
//        Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
//        Files.write(filePath, file.getBytes());

        String uploadDir = "/path/to/uploads/";

        // 디렉토리 생성 (존재하지 않을 경우)
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 고유한 파일 이름 생성
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        // 파일을 저장할 경로 설정
        Path filePath = uploadPath.resolve(uniqueFilename);

        // 파일 저장 (기존 파일 덮어쓰기 또는 고유 이름 생성 방식 중 선택)
        Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE_NEW);

        PostImage postImage = new PostImage();
        postImage.setName(uniqueFilename);  // 고유한 파일 이름을 저장
        postImage.setType(file.getContentType());
        postImage.setPath(filePath.toString()); // 파일 경로를 저장 (저장된 경로를 데이터베이스에 저장)
        postImage.setPost(postService.getById(postId));
        postImageRepository.save(postImage);

        System.out.println("File stored at: " + filePath.toString());

        return postImageMapper.imageToResponse(postImage);
    }

    public String download(int id) {
        Optional<PostImage> postImage = postImageRepository.findPostImageByPost_Id(id);
        if (postImage.isPresent()) {
            String path = postImage.get().getPath();
            System.out.println("File stored at: " + path);
            return path;
            //return postImage.get().getPath();  // 이미지 경로 반환
        }

        return null;
    }

}