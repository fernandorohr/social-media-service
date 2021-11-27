package com.fernando.socialmediaservice.post.mapper;

import com.fernando.socialmediaservice.post.entity.PostEntity;
import com.fernando.socialmediaservice.post.model.PostModel;
import com.fernando.socialmediaservice.post.model.request.CreatePostRequest;
import com.fernando.socialmediaservice.post.model.request.UpdatePostRequest;
import com.fernando.socialmediaservice.post.model.response.PostResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class PostMapper {

    public static PostModel mapRequestToModel (CreatePostRequest createPostRequest) {
        return Optional.ofNullable(createPostRequest).map(post ->
                        PostModel.builder()
                                .author(post.getAuthor())
                                .authorId(post.getAuthorId())
                                .title(post.getTitle())
                                .body(post.getBody())
                                .dateTime(LocalDateTime.now())
                                .build())
                .orElse(null);
    }

    public static PostModel mapRequestToModel (UpdatePostRequest updatePostRequest) {
        return Optional.ofNullable(updatePostRequest).map(post ->
                        PostModel.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .body(post.getBody())
                                .build())
                .orElse(null);
    }

    public static PostEntity mapModelToEntity (PostModel postModel) {
        return Optional.ofNullable(postModel).map(post ->
                        PostEntity.builder()
                                .id(post.getId())
                                .author(post.getAuthor())
                                .authorId(post.getAuthorId())
                                .title(post.getTitle())
                                .body(post.getBody())
                                .dateTime(post.getDateTime())
                                .comments(CommentMapper.mapModelListToEntityList(post.getComments()))
                                .build())
                .orElse(null);
    }

    public static Page<PostModel> mapEntityPageToModelPage(Page<PostEntity> postEntities) {
        return new PageImpl<>(
                mapEntityListToModelList(postEntities.getContent()),
                postEntities.getPageable(),
                postEntities.getTotalElements());
    }

    public static List<PostModel> mapEntityListToModelList (List<PostEntity> postEntities) {
        return postEntities.stream()
                .map(PostMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public static PostModel mapEntityToModel (PostEntity postModel) {
        return Optional.ofNullable(postModel).map(post ->
                        PostModel.builder()
                                .id(post.getId())
                                .author(post.getAuthor())
                                .authorId(post.getAuthorId())
                                .title(post.getTitle())
                                .body(post.getBody())
                                .dateTime(post.getDateTime())
                                .comments(CommentMapper.mapEntityListToModelList(post.getComments()))
                                .build())
                .orElse(null);
    }

    public static Page<PostResponse> mapModelPageToResponsePage(Page<PostModel> postEntities) {
        return new PageImpl<>(
                mapModelListToResponse(postEntities.getContent()),
                postEntities.getPageable(),
                postEntities.getTotalElements());
    }

    public static List<PostResponse> mapModelListToResponse(List<PostModel> postModels) {
        return postModels.stream()
                .sorted()
                .map(PostMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public static PostResponse mapModelToResponse (PostModel postModel) {
        return Optional.ofNullable(postModel).map(post ->
                        PostResponse.builder()
                                .id(post.getId())
                                .author(post.getAuthor())
                                .authorId(post.getAuthorId())
                                .title(post.getTitle())
                                .body(post.getBody())
                                .dateTime(post.getDateTime())
                                .comments(CommentMapper.mapModelListToResponseList(post.getComments()))
                                .build())
                .orElse(null);
    }
}
