package com.questapp.service;

import com.questapp.entity.Comment;
import com.questapp.request.CommentCreateRequest;
import com.questapp.request.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId);

    Comment getCommentById(Long commentId);

    Comment createComments(CommentCreateRequest commentCreateRequest);

    Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest);

    void deleteOneComment(Long commentId);
}
