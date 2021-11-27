package com.fernando.socialmediaservice.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "posts")
public class PostEntity {

    @Id
    private String id;
    private String author;
    private String authorId;
    private String title;
    private String body;
    private LocalDateTime dateTime;
    private List<CommentEntity> comments;
}
