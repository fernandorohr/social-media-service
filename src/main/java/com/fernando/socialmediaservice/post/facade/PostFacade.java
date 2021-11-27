package com.fernando.socialmediaservice.post.facade;

import com.fernando.socialmediaservice.post.mapper.PostMapper;
import com.fernando.socialmediaservice.post.model.PostModel;
import com.fernando.socialmediaservice.post.model.request.CreatePostRequest;
import com.fernando.socialmediaservice.post.model.request.UpdatePostRequest;
import com.fernando.socialmediaservice.post.model.response.PostResponse;
import com.fernando.socialmediaservice.post.service.PostService;
import com.fernando.socialmediaservice.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class PostFacade {

    private final PostService postService;
    private final UserService userService;

    public PostResponse create(CreatePostRequest createPostRequest) {
        return mapCreateRequestToModel()
                .andThen(postService.create())
                .andThen(addPostToUser())
                .andThen(mapModelToResponse())
                .apply(createPostRequest);

    }

    public Page<PostResponse> findAll(Integer page, Integer size) {
        return postService.findAll(size)
                .andThen(mapModelPageToResponsePage())
                .apply(page);
    }

    public void deleteById(String id) {
        postService.deleteById(id);
    }

    private Function<PostModel, PostModel> addPostToUser() {
        return post -> {
            userService.addPostToUser(post.getAuthorId(), post.getId());
            return post;
        };
    }

    private Function<CreatePostRequest, PostModel> mapCreateRequestToModel() {
        return PostMapper::mapRequestToModel;
    }

    private Function<UpdatePostRequest, PostModel> mapUpdateRequestToModel() {
        return PostMapper::mapRequestToModel;
    }

    private Function<PostModel, PostResponse> mapModelToResponse() {
        return PostMapper::mapModelToResponse;
    }

    private Function<Page<PostModel>, Page<PostResponse>> mapModelPageToResponsePage() {
        return PostMapper::mapModelPageToResponsePage;
    }
}