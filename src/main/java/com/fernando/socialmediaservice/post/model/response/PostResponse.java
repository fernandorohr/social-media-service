package com.fernando.socialmediaservice.post.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostResponse {

    private String id;
    private String author;
    private String authorId;
    private String title;
    private String body;
    private LocalDateTime dateTime;
    private List<CommentResponse> comments;
}
