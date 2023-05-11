package com.questapp.service;

import com.questapp.entity.Like;
import com.questapp.entity.Post;
import com.questapp.entity.User;
import com.questapp.exception.ErrorHandling;
import com.questapp.repository.LikeRepository;
import com.questapp.request.LikePostRequest;
import com.questapp.responses.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class LikeServiceImp implements LikeService {

    private UserService userService;
    private PostService postService;
    private LikeRepository likeRepository;

    private Logger logger = Logger.getLogger(LikeServiceImp.class.getName());

    @Autowired
    public LikeServiceImp(UserService userService, PostService postService, LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.likeRepository = likeRepository;
    }

    @Override
    public List<LikeResponse> getAllLikesWithParams(Optional<Long> postId, Optional<Long> userId) {
        List<Like> list;
        if (postId.isPresent() && userId.isPresent()) {
            logger.info("Get the list by userId and postId !!!");
            list = likeRepository.findByPostIdAndUserId(postId.get(), userId.get());
        } else if (postId.isPresent()) {
            logger.info("Get the list by postId");
            list = likeRepository.findByPostId(postId.get());
        } else if (userId.isPresent()) {
            logger.info("Get the list by userId");
            list = likeRepository.findByUserId(userId.get());
        } else {
            logger.info("Get the whole list !!!");
            list = likeRepository.findAll();
        }
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    @Override
    public Like createLike(LikePostRequest likePostRequest) {
        User user = userService.getOneUserById(likePostRequest.getUserId());
        Post post = postService.getOnePostById(likePostRequest.getPostId());
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        logger.info("Creating Like");
        return likeRepository.save(like);
    }

    @Override
    public Like getLikeById(Long likeId) {
        Optional<Like> like1 = likeRepository.findById(likeId);
        if (like1.isPresent()) {
            logger.info("Get the Like By Id");
            return like1.get();
        }
        throw new ErrorHandling("This is not acceptable ID " + likeId);
    }

    @Override
    public void deleteOneLike(Long likeId) {
        logger.info("Deleting like by Id");
        likeRepository.deleteById(likeId);
    }
}
