package com.miniproj2_back.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostImageResponse {
    private int id;
    private String name;
    private String type;
    private byte[] data;
    private int postId;
}