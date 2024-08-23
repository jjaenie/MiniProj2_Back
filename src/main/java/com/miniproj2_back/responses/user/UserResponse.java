package com.miniproj2_back.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String name;
    private String lastName;
    private String email;
//    private List<UserFollowerResponse> followers;
//    private List<UserFollowingResponse> following;
}
