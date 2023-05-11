package com.questapp.controller;

import com.questapp.entity.Post;
import com.questapp.request.PostRequest;
import com.questapp.responses.PostResponse;
import com.questapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;

    }

    @GetMapping
    public List<PostResponse> getAllPost(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @GetMapping("/{postID}")
    public PostResponse getOnePost(@PathVariable Long postID) {
        return postService.getOnePostByIdWithLikes(postID);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        return postService.updatePost(postId, postRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deleteOnePost(postId);
    }
    /*

    @GetMapping("/abc")
    public List<Post> abcd(@RequestParam Optional<Long> userId) {

        return postService.getAllPostlen(userId);
    }

     */

}
