package com.fernando.socialmediaservice.post.factory;

import com.fernando.socialmediaservice.post.model.PostModel;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class PostFactory {

    public static PostModel updatePost(PostModel newPost, PostModel oldPost) {
        return PostModel.builder()
                .id(oldPost.getId())
                .author(oldPost.getAuthor())
                .authorId(oldPost.getAuthorId())
                .title(Optional.ofNullable(newPost)
                        .map(PostModel::getTitle)
                        .orElse(oldPost.getTitle()))
                .body(Optional.ofNullable(newPost)
                        .map(PostModel::getBody)
                        .orElse(oldPost.getBody()))
                .dateTime(oldPost.getDateTime())
                .comments(oldPost.getComments())
                .build();
    }
}
