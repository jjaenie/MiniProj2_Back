package com.miniproj2_back.mappers;

import com.miniproj2_back.entity.UserImage;
import com.miniproj2_back.responses.userImage.UserImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserImageMapper {
    @Mapping(source = "user.id",target = "userId")
    UserImageResponse userImageToResponse(UserImage userImage);
}
