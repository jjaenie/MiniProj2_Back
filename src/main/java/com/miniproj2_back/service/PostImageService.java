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
import java.util.Optional;

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

    public PostImageResponse upload(MultipartFile file, int postId) throws IOException {
        PostImage postImage = new PostImage();
        postImage.setName(file.getOriginalFilename());
        postImage.setType(file.getContentType());
        postImage.setData(ImageUtil.compressImage(file.getBytes()));
        postImage.setPost(postService.getById(postId));
        postImageRepository.save(postImage);
        return postImageMapper.imageToResponse(postImage);
    }

    public byte[] download(int id){
        Optional<PostImage> postImage = postImageRepository.findPostImageByPost_Id(id);
        if (postImage.isPresent()){
            return ImageUtil.decompressImage(postImage.get().getData());
        }
        return null;
    }
}