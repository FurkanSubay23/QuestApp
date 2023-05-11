package com.questapp.request;

import lombok.Data;

@Data
public class PostRequest {

    private long userId;
    private String title;
    private String text;
}
