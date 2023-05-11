package com.questapp.service;

import com.questapp.entity.Post;
import com.questapp.request.PostRequest;
import com.questapp.responses.PostResponse;
import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostResponse> getAllPosts(Optional<Long> userID);

    Post getOnePostById(Long id);

    Post createPost(PostRequest postRequest);

    Post updatePost(Long postId, PostRequest postRequest);

    void deleteOnePost(Long postId);

    PostResponse getOnePostByIdWithLikes(Long id);

   // List<Post> getAllPostlen(Optional<Long> userId);
}
