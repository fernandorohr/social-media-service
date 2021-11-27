package com.fernando.socialmediaservice.user.factory;

import com.fernando.socialmediaservice.user.model.UserModel;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class UserFactory {

    public static UserModel updateUser(UserModel newUser, UserModel oldUser) {
        return UserModel.builder()
                .id(oldUser.getId())
                .email(Optional.ofNullable(newUser)
                        .map(UserModel::getEmail)
                        .orElse(oldUser.getEmail()))
                .password(Optional.ofNullable(newUser)
                        .map(UserModel::getPassword)
                        .orElse(oldUser.getPassword()))
                .build();
    }
}
