package com.fernando.socialmediaservice.post.repository;

import com.fernando.socialmediaservice.post.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<PostEntity, String> {

    Page<PostEntity> findAllByAuthorId(String authorId, Pageable pageable);
}
