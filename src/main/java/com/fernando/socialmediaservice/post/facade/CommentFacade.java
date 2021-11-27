package com.fernando.socialmediaservice.post.facade;

import com.fernando.socialmediaservice.post.mapper.CommentMapper;
import com.fernando.socialmediaservice.post.mapper.PostMapper;
import com.fernando.socialmediaservice.post.model.CommentModel;
import com.fernando.socialmediaservice.post.model.PostModel;
import com.fernando.socialmediaservice.post.model.request.CommentRequest;
import com.fernando.socialmediaservice.post.model.response.CommentResponse;
import com.fernando.socialmediaservice.post.model.response.PostResponse;
import com.fernando.socialmediaservice.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class CommentFacade {

    private PostService postService;

    public PostResponse addComment(CommentRequest commentRequest, String postId) {
        return mapRequestToModel()
                .andThen(postService.addComment(postId))
                .andThen(mapModelToResponse())
                .apply(commentRequest);
    }

    private Function<CommentRequest, CommentModel> mapRequestToModel() {
        return CommentMapper::mapRequestToModel;
    }

    private Function<PostModel, PostResponse> mapModelToResponse() {
        return PostMapper::mapModelToResponse;
    }
}
