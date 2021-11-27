package com.fernando.socialmediaservice.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentEntity {

    private String author;
    private String authorId;
    private String body;
    private LocalDateTime dateTime;
}
