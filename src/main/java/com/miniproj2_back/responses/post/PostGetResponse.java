package com.miniproj2_back.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostGetResponse {
    private int id;
    private int userId;
    private String userName;
    private String userLastName;
    private String Description;
    private boolean hasImage;  // 이미지 존재 여부를 나타내는 필드 추가
}
