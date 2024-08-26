package com.miniproj2_back.service;

import com.miniproj2_back.entity.Follow;
import com.miniproj2_back.mappers.FollowMapper;
import com.miniproj2_back.repository.FollowRepository;
import com.miniproj2_back.requests.FollowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserService userService;

    public FollowService(FollowRepository followRepository, FollowMapper followMapper, UserService userService) {
        this.followRepository = followRepository;
        this.followMapper = followMapper;
        this.userService = userService;
    }

    public void add(FollowRequest followAddRequest){
        if (userService.isFollowing(followAddRequest.getUserId(), followAddRequest.getFollowingId())){
            return;
        }
        followRepository.save(followMapper.addRequestToFollow(followAddRequest));
    }

    public  void delete(FollowRequest followRequest){
        Follow follow
                = followRepository.findByUser_IdAndFollowing_Id(followRequest.getUserId(), followRequest.getFollowingId()).orElse(null);
        followRepository.delete(follow);
    }

}
