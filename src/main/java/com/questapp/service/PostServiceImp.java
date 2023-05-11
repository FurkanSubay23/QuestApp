package com.questapp.service;

import com.questapp.entity.Post;
import com.questapp.entity.User;
import com.questapp.exception.ErrorHandling;
import com.questapp.repository.PostRepository;
import com.questapp.request.PostRequest;
import com.questapp.responses.LikeResponse;
import com.questapp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;

    private static Logger logger = Logger.getLogger(PostService.class.getName());

    @Autowired
    public PostServiceImp(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    @Override
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> postList;
        if (userId.isPresent()) {
            logger.info("Test User Id = " + String.valueOf(userService.getOneUserById(userId.get())));
            postList = postRepository.findByUserId(userId.get());
        } else {
            logger.info("ALL User :");
            postList = postRepository.findAll();
        }

        return postList.stream().map(post -> {
            List<LikeResponse> likes = likeService.getAllLikesWithParams(Optional.of(post.getId()), Optional.ofNullable(null));
            return new PostResponse(post, likes);
        }).collect(Collectors.toList());


    }


    @Override
    public Post getOnePostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new ErrorHandling("This is not acceptable ID " + id);
        }
    }

    @Override
    public Post createPost(PostRequest postRequest) {
        User user = userService.getOneUserById(postRequest.getUserId());
        // System.out.println("User ID: " + postRequest.getUserID());
        logger.info("User ID: " + postRequest.getUserId());       // check the User ID
        if (user == null) {
            throw new ErrorHandling("This is not acceptable ID " + postRequest.getUserId());
        }
        Post post = new Post();
        post.setUser(user);
        post.setTitle(postRequest.getTitle());
        post.setText(postRequest.getText());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, PostRequest postRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post newPost = post.get();
            newPost.setTitle(postRequest.getTitle());
            newPost.setText(postRequest.getText());
            logger.info("Updating Post Object !!!");
            return postRepository.save(newPost);
        } else {
            throw new ErrorHandling("This is not acceptable ID " + postId);
        }


    }

    @Override
    public void deleteOnePost(Long postId) {
        logger.info("Deleting Post !!!");
        postRepository.deleteById(postId);
    }

    @Override
    public PostResponse getOnePostByIdWithLikes(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            List<LikeResponse> list = likeService.getAllLikesWithParams(Optional.of(post.get().getId()), Optional.ofNullable(null));
            return new PostResponse(post.get(), list);
        }
        throw new ErrorHandling("This is not acceptable ID " + id);
    }
    /*
    @Override
    public List<Post> getAllPostlen(Optional<Long> userId) {
        List<Post> postList;
        if (userId.isPresent()) {
            logger.info("Test User Id = " + String.valueOf(userService.getOneUserById(userId.get())));
            postList = postRepository.findByUserId(userId.get());
        } else {
            logger.info("ALL User :");
            postList = postRepository.findAll();
        }
        return postList;
    }


     */
}
