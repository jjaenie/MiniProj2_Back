package com.miniproj2_back.mappers;

import com.miniproj2_back.entity.Follow;
import com.miniproj2_back.entity.User;
import com.miniproj2_back.requests.UserAddRequest;
import com.miniproj2_back.responses.user.UserFollowerResponse;
import com.miniproj2_back.responses.user.UserFollowingResponse;
import com.miniproj2_back.responses.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "following", ignore = true)
    @Mapping(target = "followers", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "comments", ignore = true)
    User requestToUser(UserAddRequest userAddRequest);

    List<UserResponse> usersToResponses(List<User> users);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "followers", ignore = true)
    @Mapping(target = "following", ignore = true)
    UserResponse userToResponse(User user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.lastName", target = "lastName")
    UserFollowerResponse followToFollowerResponse(Follow follow);

    @Mapping(source = "following.id", target = "userId")
    @Mapping(source = "following.lastName", target = "lastName")
    @Mapping(source = "following.name", target = "name")
    UserFollowingResponse followToFollowingResponse(Follow follow);

    List<UserFollowerResponse> followsToFollowerResponses(List<Follow> follows);
    List<UserFollowingResponse> followsToFollowingResponses(List<Follow> follows);
}
