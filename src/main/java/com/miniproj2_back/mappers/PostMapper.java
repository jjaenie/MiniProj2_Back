package com.miniproj2_back.mappers;

import com.miniproj2_back.entity.Post;
import com.miniproj2_back.requests.PostAddRequest;
import com.miniproj2_back.responses.post.PostGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(target = "hasImage", ignore = true)  // 수동으로 설정할 필드는 무시
    PostGetResponse postToGetResponse(Post post);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "id", ignore = true)  // ID는 생성 시 무시
    @Mapping(target = "likes", ignore = true)  // Likes 필드 무시
    @Mapping(target = "postImages", ignore = true)  // PostImages 필드 무시
    @Mapping(target = "comments", ignore = true)  // Comments 필드 무시
    Post postAddRequestToPost(PostAddRequest postAddRequest);

    List<PostGetResponse> postsToGetResponses(List<Post> posts);

    // 수동으로 hasImage 필드를 설정하기 위한 메소드
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "userLastName", ignore = true)
    @Mapping(target = "description", ignore = true)
    void updateHasImage(@MappingTarget PostGetResponse response, Boolean hasImage);
}

