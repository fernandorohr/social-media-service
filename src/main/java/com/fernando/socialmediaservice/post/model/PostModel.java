package com.fernando.socialmediaservice.post.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostModel {

    private String id;
    private String author;
    private String authorId;
    private String title;
    private String body;
    private LocalDateTime dateTime;
    private List<CommentModel> comments;
}
