package com.miniproj2_back.mappers;

import com.miniproj2_back.entity.Like;
import com.miniproj2_back.requests.LikeRequest;
import com.miniproj2_back.responses.like.LikeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "user.name",target = "name")
    @Mapping(source = "user.lastName",target = "lastName")
    LikeResponse likeToPostLikeResponse(Like like);

    @Mapping(source = "postId",target = "post.id")
    @Mapping(source = "userId",target = "user.id")
    Like requestToLike(LikeRequest likeRequest);

    List<LikeResponse> likesToLikeResponses(List<Like> likes);

}