package com.fernando.socialmediaservice.user.mapper;

import com.fernando.socialmediaservice.user.entity.UserEntity;
import com.fernando.socialmediaservice.user.model.UserModel;
import com.fernando.socialmediaservice.user.model.request.CreateUserRequest;
import com.fernando.socialmediaservice.user.model.request.UpdateUserRequest;
import com.fernando.socialmediaservice.user.model.response.UserResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {

    public static UserModel mapRequestToModel(CreateUserRequest createUserRequest) {
        return Optional.ofNullable(createUserRequest).map(user ->
                        UserModel.builder()
                                .name(user.getName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .build())
                .orElse(null);
    }

    public static UserModel mapRequestToModel(UpdateUserRequest updateUserRequest) {
        return Optional.ofNullable(updateUserRequest).map(user ->
                        UserModel.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .build())
                .orElse(null);
    }

    public static UserEntity mapModelToEntity(UserModel userModel) {
        return Optional.ofNullable(userModel).map(user ->
                        UserEntity.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .postIds(user.getPostIds())
                                .build())
                .orElse(null);
    }

    public static UserModel mapEntityToModel(UserEntity userEntity) {
        return Optional.ofNullable(userEntity).map(user ->
                        UserModel.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .postIds(user.getPostIds())
                                .build())
                .orElse(null);
    }

    public static UserResponse mapModelToResponse(UserModel userModel) {
        return Optional.ofNullable(userModel).map(user ->
                        UserResponse.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .postIds(user.getPostIds())
                                .build())
                .orElse(null);
    }

    public static List<UserResponse> mapModelListToResponseList(List<UserModel> users) {
        return users.stream()
                .map(UserMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }
}
