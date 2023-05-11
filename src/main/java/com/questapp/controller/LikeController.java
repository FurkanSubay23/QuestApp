package com.questapp.controller;


import com.questapp.entity.Like;
import com.questapp.request.LikePostRequest;
import com.questapp.responses.LikeResponse;
import com.questapp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;

    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        return likeService.getAllLikesWithParams(postId, userId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikePostRequest likePostRequest) {
        return likeService.createLike(likePostRequest);
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLikeById(@PathVariable Long likeId) {
        likeService.deleteOneLike(likeId);
    }
}
