package com.miniproj2_back.service;

import com.miniproj2_back.mappers.UserMapper;
import com.miniproj2_back.entity.User;
import com.miniproj2_back.repository.UserRepository;
import com.miniproj2_back.requests.UserAddRequest;
import com.miniproj2_back.responses.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    //private final FollowRepository followRepository;

//    public UserService(UserMapper userMapper, UserRepository userRepository, FollowRepository followRepository) {
//        this.userMapper = userMapper;
//        this.userRepository = userRepository;
//        this.followRepository = followRepository;
//    }

public UserService(UserMapper userMapper, UserRepository userRepository) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
}

    public List<UserResponse> getAll(){

        return userMapper.usersToResponses(userRepository.findAll());
    }
    public UserResponse getResponseById(int id){
        User user = userRepository.findById(id).orElse(null);
        return userMapper.userToResponse(user);
    }

    public UserResponse getByEmail(String email){
        User user = userRepository.findByEmail(email);
        return userMapper.userToResponse(user);
    }

//    public List<UserFollowingResponse> getUserFollowing(int userId){
//        return userMapper.followsToFollowingResponses(followRepository.findAllByUser_Id(userId));
//    }
//
//    public boolean isFollowing(int userId,int followingId){
//        Optional<Follow> follow = followRepository.findByUser_IdAndFollowing_Id(userId,followingId);
//        return follow.isPresent();
//    }

    public User getById(int id){
        return userRepository.findById(id).get();
    }
    public void add(UserAddRequest userAddRequest){
        User user = userMapper.requestToUser(userAddRequest);
        userRepository.save(user);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }
}
