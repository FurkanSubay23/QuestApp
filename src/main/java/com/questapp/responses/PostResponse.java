package com.questapp.responses;

import com.questapp.entity.Like;
import com.questapp.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private long id;
    private long userId;
    private String userName;
    private String title;
    private String text;
    private List<LikeResponse> postLikes;



    public PostResponse(Post entity, List<LikeResponse> postLikes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = postLikes;

    }
}
