package com.fernando.socialmediaservice.post.service;

import com.fernando.socialmediaservice.exception.InternalServerErrorException;
import com.fernando.socialmediaservice.post.mapper.PostMapper;
import com.fernando.socialmediaservice.post.model.PostModel;
import com.fernando.socialmediaservice.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Function<PostModel, PostModel> create() {
        return this::save;
    }

    public Function<Integer, Page<PostModel>> findAll(Integer size) {
        return page -> {
            var pageable = PageRequest.of(page, size);
            return PostMapper.mapEntityPageToModelPage(postRepository.findAll(pageable));
        };
    }

    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    private PostModel save(PostModel postModel) {
        return Optional.of(postRepository.save(PostMapper.mapModelToEntity(postModel)))
                .map(PostMapper::mapEntityToModel)
                .orElseThrow(() -> new InternalServerErrorException("Falha ao salvar novo Post"));

    }
}
