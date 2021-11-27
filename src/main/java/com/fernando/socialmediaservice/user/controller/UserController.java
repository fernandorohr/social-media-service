package com.fernando.socialmediaservice.user.controller;

import com.fernando.socialmediaservice.exception.ConflictExceptionDetails;
import com.fernando.socialmediaservice.exception.InternalServerErrorExceptionDetails;
import com.fernando.socialmediaservice.exception.NotFoundExceptionDetails;
import com.fernando.socialmediaservice.exception.PreConditionFailedExceptionDetails;
import com.fernando.socialmediaservice.user.facade.UserFacade;
import com.fernando.socialmediaservice.user.model.request.CreateUserRequest;
import com.fernando.socialmediaservice.user.model.request.UpdateUserRequest;
import com.fernando.socialmediaservice.user.model.response.UserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = UserResponse.class),
            @ApiResponse(code = 412, message = "CONFLICT", response = ConflictExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public UserResponse create(@RequestBody @Valid @NotNull CreateUserRequest createUserRequest) {
        return userFacade.create(createUserRequest);
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Atualiza um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = UserResponse.class),
            @ApiResponse(code = 412, message = "NOT FOUND", response = NotFoundExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public UserResponse update(@RequestBody @Valid @NotNull UpdateUserRequest updateUserRequest) {
        return userFacade.update(updateUserRequest);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Faz login e retorna dados do usuário logado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = UserResponse.class),
            @ApiResponse(code = 412, message = "PRECONDITION FAILED", response = PreConditionFailedExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public UserResponse login(@RequestParam @NotBlank String email, @RequestParam @NotBlank String password) {
        return userFacade.login(email, password);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deleta o usuario")
    @ApiResponses({
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 412, message = "PRECONDITION FAILED", response = PreConditionFailedExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public void deleteById(@PathVariable("id") @NotBlank String id) {
        userFacade.deleteById(id);
    }
}
