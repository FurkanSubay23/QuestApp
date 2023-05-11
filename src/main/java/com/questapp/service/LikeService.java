package com.questapp.service;

import com.questapp.entity.Like;
import com.questapp.request.LikePostRequest;
import com.questapp.responses.LikeResponse;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<LikeResponse> getAllLikesWithParams(Optional<Long> postId, Optional<Long> userId);


    Like createLike(LikePostRequest likePostRequest);

    Like getLikeById(Long likeId);

    void deleteOneLike(Long likeId);
}
