package com.fernando.socialmediaservice.post.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {

    private String author;
    private String authorId;
    private String body;
    private LocalDateTime dateTime;
}
