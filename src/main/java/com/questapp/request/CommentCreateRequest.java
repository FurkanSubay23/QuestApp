package com.questapp.request;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private long postId;
    private long userId;
    private String text;

}
