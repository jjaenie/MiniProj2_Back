package com.miniproj2_back.mappers;

import com.miniproj2_back.entity.PostImage;
import com.miniproj2_back.responses.post.PostImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostImageMapper {

    @Mapping(source = "post.id",target = "postId")
    PostImageResponse imageToResponse(PostImage postImage);

}
