package com.miniproj2_back.mappers;

import com.miniproj2_back.entity.User;
import com.miniproj2_back.requests.UserAddRequest;
import com.miniproj2_back.responses.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToUser(UserAddRequest userAddRequest);

    List<UserResponse> usersToResponses(List<User> users);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    UserResponse userToResponse(User user);


    // @Mapping(source = "user.id", target = "userId")
    // @Mapping(source = "user.name", target = "name")
    // @Mapping(source = "user.lastName", target = "lastName")
    // UserFollowerResponse followToFollowerResponse(Follow follow);

    // @Mapping(source = "following.id", target = "userId")
    // @Mapping(source = "following.lastName", target = "lastName")
    // @Mapping(source = "following.name", target = "name")
    // UserFollowingResponse followToFollowingResponse(Follow follow);

    // @Mapping(source = "followers", target = "followers")
    // @Mapping(source = "following", target = "following")
    // UserResponse userToResponse(User user);

    // List<UserFollowingResponse> followsToFollowingResponses(List<Follow> follows);
}
