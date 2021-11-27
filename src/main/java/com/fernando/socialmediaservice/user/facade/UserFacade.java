package com.fernando.socialmediaservice.user.facade;

import com.fernando.socialmediaservice.user.mapper.UserMapper;
import com.fernando.socialmediaservice.user.model.UserModel;
import com.fernando.socialmediaservice.user.model.request.CreateUserRequest;
import com.fernando.socialmediaservice.user.model.request.UpdateUserRequest;
import com.fernando.socialmediaservice.user.model.response.UserResponse;
import com.fernando.socialmediaservice.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class UserFacade {

    UserService userService;

    public UserResponse create(CreateUserRequest createUserRequest) {
        return mapCreateToModel()
                .andThen(userService.create())
                .andThen(mapToResponse())
                .apply(createUserRequest);
    }

    public UserResponse update(UpdateUserRequest updateUserRequest) {
        return mapUpdateToModel()
                .andThen(userService.update())
                .andThen(mapToResponse())
                .apply(updateUserRequest);
    }

    public UserResponse login(String email, String password) {
        return userService.login(password)
                .andThen(mapToResponse())
                .apply(email);
    }

    public void deleteById(String id) {
        userService.deleteById(id);
    }

    private Function<CreateUserRequest, UserModel> mapCreateToModel() {
        return UserMapper::mapRequestToModel;
    }

    private Function<UpdateUserRequest, UserModel> mapUpdateToModel() {
        return UserMapper::mapRequestToModel;
    }

    private Function<UserModel, UserResponse> mapToResponse() {
        return UserMapper::mapModelToResponse;
    }
}
