package com.fernando.socialmediaservice.post.mapper;

import com.fernando.socialmediaservice.post.entity.CommentEntity;
import com.fernando.socialmediaservice.post.model.CommentModel;
import com.fernando.socialmediaservice.post.model.request.CommentRequest;
import com.fernando.socialmediaservice.post.model.response.CommentResponse;
import lombok.experimental.UtilityClass;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class CommentMapper {

    public static CommentModel mapRequestToModel(CommentRequest commentRequest) {
        return Optional.ofNullable(commentRequest).map(comment ->
                        CommentModel.builder()
                                .authorId(comment.getAuthorId())
                                .author(comment.getAuthor())
                                .body(comment.getBody())
                                .dateTime(LocalDateTime.now())
                                .build())
                .orElse(null);
    }

    public static List<CommentEntity> mapModelListToEntityList(List<CommentModel> commentModels) {
        if (ObjectUtils.isEmpty(commentModels)) return Collections.emptyList();
        return commentModels.stream()
                .map(CommentMapper::mapModelToEntity)
                .collect(Collectors.toList());
    }

    public static CommentEntity mapModelToEntity(CommentModel commentModels) {
        return Optional.ofNullable(commentModels).map(comment ->
                        CommentEntity.builder()
                                .authorId(comment.getAuthorId())
                                .author(comment.getAuthor())
                                .body(comment.getBody())
                                .dateTime(comment.getDateTime())
                                .build())
                .orElse(null);
    }

    public static List<CommentModel> mapEntityListToModelList(List<CommentEntity> commentEntities) {
        if (ObjectUtils.isEmpty(commentEntities)) return Collections.emptyList();
        return commentEntities.stream()
                .map(CommentMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public static CommentModel mapEntityToModel(CommentEntity commentEntity) {
        return Optional.ofNullable(commentEntity).map(comment ->
                        CommentModel.builder()
                                .authorId(comment.getAuthorId())
                                .author(comment.getAuthor())
                                .body(comment.getBody())
                                .dateTime(comment.getDateTime())
                                .build())
                .orElse(null);
    }

    public static List<CommentResponse> mapModelListToResponseList(List<CommentModel> commentModels) {
        if (ObjectUtils.isEmpty(commentModels)) return Collections.emptyList();
        return commentModels.stream()
                .sorted()
                .map(CommentMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public static CommentResponse mapModelToResponse(CommentModel commentModel) {
        return Optional.ofNullable(commentModel).map(comment ->
                        CommentResponse.builder()
                                .authorId(comment.getAuthorId())
                                .author(comment.getAuthor())
                                .body(comment.getBody())
                                .dateTime(comment.getDateTime())
                                .build())
                .orElse(null);
    }
}
