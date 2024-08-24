package com.miniproj2_back.controller;

import com.miniproj2_back.requests.LikeRequest;
import com.miniproj2_back.responses.like.LikeResponse;
import com.miniproj2_back.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody LikeRequest likeRequest){
        likeService.add(likeRequest);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    @GetMapping("/getallbypost/{postId}")
    public ResponseEntity<List<LikeResponse>> getAllByPost(@PathVariable int postId){
        return new ResponseEntity<>(likeService.getAllByPost(postId), HttpStatus.OK);
    }

    @GetMapping("/getallbyuser/{userId}")
    public ResponseEntity<List<LikeResponse>> getAllByUser(@PathVariable int userId){
        return new ResponseEntity<>(likeService.getAllByUser(userId),HttpStatus.OK);
    }

    @GetMapping("/isliked")
    public ResponseEntity<Boolean> isLiked(@RequestParam int userId,@RequestParam int postId){
        return new ResponseEntity<>(likeService.isLiked(userId,postId),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody LikeRequest likeRequest){
        likeService.delete(likeRequest);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
}
