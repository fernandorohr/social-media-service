package com.fernando.socialmediaservice.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    private String id;
    private String name;
    private String email;
    private String password;
    private Set<String> postIds;
}
