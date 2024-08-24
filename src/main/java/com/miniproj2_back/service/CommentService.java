package com.miniproj2_back.service;

import com.miniproj2_back.entity.Comment;
import com.miniproj2_back.mappers.CommentMapper;
import com.miniproj2_back.repository.CommentRepository;
import com.miniproj2_back.requests.CommentAddRequest;
import com.miniproj2_back.requests.CommentUpdateRequest;
import com.miniproj2_back.responses.comment.CommentGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public void add(CommentAddRequest commentAddRequest){
        Comment comment = commentMapper.addRequestToComment(commentAddRequest);
        commentRepository.save(comment);
    }

    public List<CommentGetResponse> getAll(){
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.commentsToResponses(comments);
    }

    public CommentGetResponse getById(int id){
        Comment comment = commentRepository.findById(id).orElse(null);
        return  commentMapper.commentToResponse(comment);
    }

    public List<CommentGetResponse> getAllByPost(int postId){
        List<Comment> comments = commentRepository.findAllByPost_Id(postId);
        return commentMapper.commentsToResponses(comments);
    }

    public List<CommentGetResponse> getAllByUser(int userId){
        List<Comment> comments = commentRepository.findAllByUser_Id(userId);
        return commentMapper.commentsToResponses(comments);
    }
    public void update(int id, CommentUpdateRequest commentUpdateRequest){
        Comment commentToUpdate = commentRepository.findById(id).orElse(null);
        if (commentToUpdate!=null){
            commentToUpdate.setDescription(commentUpdateRequest.getDescription());
        }
    }

    public void delete(int id){
        commentRepository.deleteById(id);
    }
}
