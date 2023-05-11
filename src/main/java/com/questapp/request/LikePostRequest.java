package com.questapp.request;

import lombok.Data;

@Data
public class LikePostRequest {
    private long userId;
    private long postId;
}
