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
public class CommentModel implements Comparable<CommentModel> {

    private String author;
    private String authorId;
    private String body;
    private LocalDateTime dateTime;

    @Override
    public int compareTo(CommentModel o) {
        return o.getDateTime().compareTo(this.dateTime);
    }
}
