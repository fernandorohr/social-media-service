package com.fernando.socialmediaservice.user.service;

import com.fernando.socialmediaservice.exception.ConflictException;
import com.fernando.socialmediaservice.exception.InternalServerErrorException;
import com.fernando.socialmediaservice.exception.NotFoundException;
import com.fernando.socialmediaservice.exception.PreConditionFailedException;
import com.fernando.socialmediaservice.user.factory.UserFactory;
import com.fernando.socialmediaservice.user.mapper.UserMapper;
import com.fernando.socialmediaservice.user.model.UserModel;
import com.fernando.socialmediaservice.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Function<UserModel, UserModel> create() {
        return userModel ->  {
            if (Objects.nonNull(userRepository.findByEmail(userModel.getEmail())))
                throw new ConflictException(String.format("Email %s já está cadastrado", userModel.getEmail()));
            return save(userModel);
        };
    }

    public Function<UserModel, UserModel> update() {
        return newUser -> UserFactory.updateUser(newUser, findById(newUser.getId()));
    }

    public Function<String, UserModel> login(String password) {
        return email -> userRepository.findByEmailAndPassword(email, password)
                .map(UserMapper::mapEntityToModel)
                .orElseThrow(() -> new PreConditionFailedException("Login e senha incorretos"));
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public void addPostToUser(String authorId, String postId) {
        var userModel = findById(authorId);
        addPostToList(userModel, postId);
        save(userModel);
    }

    public BiConsumer<String, String> deletePost() {
        return (userId, postId) -> {
            var userModel = findById(userId);
            userModel.getPostIds().remove(postId);
            save(userModel);
        };
    }

    private UserModel save(UserModel userModel) {
        return Optional.of(userRepository.save(UserMapper.mapModelToEntity(userModel)))
                .map(UserMapper::mapEntityToModel)
                .orElseThrow(() -> new InternalServerErrorException("Falha ao salvar usuario"));
    }

    private UserModel findById(String id) {
        return userRepository.findById(id)
                .map(UserMapper::mapEntityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário %s não encontrado", id)));
    }

    private void addPostToList(UserModel userModel, String postId) {
        if (Objects.nonNull(userModel.getPostIds()))
            userModel.getPostIds().add(postId);
        else
            userModel.setPostIds(new HashSet<>(Collections.singleton(postId)));
    }
}
