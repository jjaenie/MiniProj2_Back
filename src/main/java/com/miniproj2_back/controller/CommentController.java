package com.miniproj2_back.controller;

import com.miniproj2_back.requests.CommentAddRequest;
import com.miniproj2_back.responses.comment.CommentGetResponse;
import com.miniproj2_back.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CommentGetResponse>> getAll(){
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getallbypost/{postId}")
    public ResponseEntity<List<CommentGetResponse>> getAllByPost(@PathVariable int postId){
        return new ResponseEntity<>(commentService.getAllByPost(postId), HttpStatus.OK);
    }

    @GetMapping("/getallbyuser/{userId}")
    public ResponseEntity<List<CommentGetResponse>> getAllByUser(@PathVariable int userId){
        return new ResponseEntity<>(commentService.getAllByUser(userId),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CommentAddRequest commentAddRequest){
        commentService.add(commentAddRequest);
        return new ResponseEntity<>("Added",HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id){
        commentService.delete(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
}
