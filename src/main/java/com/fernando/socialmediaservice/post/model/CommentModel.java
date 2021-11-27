package com.fernando.socialmediaservice.post.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentModel {

    private String author;
    private String authorId;
    private String body;
    private LocalDateTime dateTime;
}
