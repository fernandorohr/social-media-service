package com.fernando.socialmediaservice.post.service;

import com.fernando.socialmediaservice.exception.InternalServerErrorException;
import com.fernando.socialmediaservice.exception.NotFoundException;
import com.fernando.socialmediaservice.post.factory.PostFactory;
import com.fernando.socialmediaservice.post.mapper.PostMapper;
import com.fernando.socialmediaservice.post.model.CommentModel;
import com.fernando.socialmediaservice.post.model.PostModel;
import com.fernando.socialmediaservice.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Function<PostModel, PostModel> create() {
        return this::save;
    }

    public Function<PostModel, PostModel> update() {
        return postModel -> {
            postModel = PostFactory.updatePost(postModel, findById(postModel.getId()));
            return save(postModel);
        };
    }

    public Function<Integer, Page<PostModel>> findAll(Integer size) {
        return page -> {
            var pageable = PageRequest.of(page, size);
            return PostMapper.mapEntityPageToModelPage(postRepository.findAll(pageable));
        };
    }

    public Function<String, Page<PostModel>> findAllByAuthorId(Integer page, Integer size) {
        return authorId -> {
            var pageable = PageRequest.of(page, size);
            return PostMapper.mapEntityPageToModelPage(postRepository.findAllByAuthorId(authorId, pageable));
        };
    }

    public BiConsumer<String, String> deleteById() {
        return (userId, postId) -> postRepository.deleteById(postId);
    }

    private PostModel save(PostModel postModel) {
        return Optional.of(postRepository.save(PostMapper.mapModelToEntity(postModel)))
                .map(PostMapper::mapEntityToModel)
                .orElseThrow(() -> new InternalServerErrorException("Falha ao salvar novo Post"));

    }

    public Function<CommentModel, PostModel> addComment(String postId) {
        return commentModel -> {
            var postModel = findById(postId);
            addCommentToList(postModel, commentModel);
            return save(postModel);
        };
    }

    private PostModel findById(String id) {
        return postRepository.findById(id)
                .map(PostMapper::mapEntityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Post %s não encontrado", id)));
    }

    private void addCommentToList(PostModel postModel, CommentModel commentModel) {
        if (ObjectUtils.isEmpty(postModel.getComments()))
            postModel.setComments(Collections.singletonList(commentModel));
        else
            postModel.getComments().add(commentModel);
    }
}
