package com.questapp.service;

import com.questapp.entity.Comment;
import com.questapp.entity.Post;
import com.questapp.entity.User;
import com.questapp.exception.ErrorHandling;
import com.questapp.repository.CommentRepository;
import com.questapp.request.CommentCreateRequest;
import com.questapp.request.CommentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CommentServiceImp implements CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    private static Logger logger = Logger.getLogger(CommentServiceImp.class.getName());

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<Comment> getAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            logger.info("Get Comment with postid and userid");
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            logger.info("get comment with userid");
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            logger.info("get comment with postid");
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    @Override
    public Comment getCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            logger.info("Get comment with byId");
            return comment.get();
        } else {
            throw new ErrorHandling("This is not acceptable Id " + commentId);
        }
    }

    @Override
    public Comment createComments(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUserById(commentCreateRequest.getUserId());
        Post post = postService.getOnePostById(commentCreateRequest.getPostId());
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setText(commentCreateRequest.getText());
        logger.info("Create Comment !!!");
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment1 = commentRepository.findById(commentId);
        if (comment1.isPresent()) {
            Comment comment = comment1.get();
            comment.setText(commentUpdateRequest.getText());
            logger.info("Updating Comment");
            return commentRepository.save(comment);
        }
        throw new ErrorHandling("We could not update the comment");
    }

    @Override
    public void deleteOneComment(Long commentId) {
        logger.info("Deleting comment by Id");
        commentRepository.deleteById(commentId);
    }
}
