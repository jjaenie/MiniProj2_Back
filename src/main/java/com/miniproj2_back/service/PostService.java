package com.miniproj2_back.service;

import com.miniproj2_back.entity.Post;
import com.miniproj2_back.mappers.PostMapper;
import com.miniproj2_back.repository.PostRepository;
import com.miniproj2_back.requests.PostAddRequest;
import com.miniproj2_back.responses.post.PostGetResponse;
import com.miniproj2_back.responses.user.UserFollowingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PostImageService postImageService;

    public PostService(PostRepository postRepository, PostMapper postMapper, UserService userService, PostImageService postImageService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userService = userService;
        this.postImageService = postImageService;
    }

    public List<PostGetResponse> getAll(){
        List<Post> posts = postRepository.findAll();
        return mapPostsToGetResponsesWithImages(posts);
    }

    public PostGetResponse getResponseById(int id){
        Post post = postRepository.findById(id).orElse(null);
        return mapPostToGetResponseWithImage(post);
    }

    public Post getById(int id){
        return postRepository.findById(id).get();
    }

    public List<PostGetResponse> getAllByUser(int userId){
        List<Post> userPosts = postRepository.findAllByUser_IdOrderByIdDesc(userId);
        return mapPostsToGetResponsesWithImages(userPosts);
    }

    public List<PostGetResponse> getByUserFollowing(int userId) {
        List<UserFollowingResponse> follows = userService.getUserFollowing(userId);
        List<Post> set = new ArrayList<>();

        for(UserFollowingResponse user : follows) {
            set.addAll(postRepository.findAllByUser_IdOrderByIdDesc(user.getUserId()));
        }

        set.sort(Comparator.comparing(Post::getId).reversed());

        return mapPostsToGetResponsesWithImages(set);
    }

    public int add(PostAddRequest postAddRequest){
        Post post =  postMapper.postAddRequestToPost(postAddRequest);
        postRepository.save(post);
        return post.getId();
    }

    public void delete(int id){
        postRepository.deleteById(id);
    }

    // Post 목록을 이미지 존재 여부를 포함한 PostGetResponse로 변환하는 메소드
    private List<PostGetResponse> mapPostsToGetResponsesWithImages(List<Post> posts) {
        List<PostGetResponse> responses = new ArrayList<>();
        for (Post post : posts) {
            responses.add(mapPostToGetResponseWithImage(post));
        }
        return responses;
    }

    // 단일 Post를 이미지 존재 여부를 포함한 PostGetResponse로 변환하는 메소드
    private PostGetResponse mapPostToGetResponseWithImage(Post post) {
        PostGetResponse response = postMapper.postToGetResponse(post);
        boolean hasImage = postImageService.hasImage(post.getId());
        response.setHasImage(hasImage);  // 수동으로 hasImage 필드를 설정
        return response;
    }
}
