package com.questapp.responses;

import com.questapp.entity.Like;
import lombok.Data;

@Data
public class LikeResponse {

    private long id;
    private long userId;
    private long postId;

    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }

}
