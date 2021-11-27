package com.fernando.socialmediaservice.post.controller;

import com.fernando.socialmediaservice.exception.BadRequestExceptionDetails;
import com.fernando.socialmediaservice.exception.ConflictExceptionDetails;
import com.fernando.socialmediaservice.exception.InternalServerErrorExceptionDetails;
import com.fernando.socialmediaservice.post.facade.PostFacade;
import com.fernando.socialmediaservice.post.model.request.CreatePostRequest;
import com.fernando.socialmediaservice.post.model.response.PostResponse;
import com.fernando.socialmediaservice.user.model.request.CreateUserRequest;
import com.fernando.socialmediaservice.user.model.response.UserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/post")
@AllArgsConstructor
public class PostController {

    private final PostFacade postFacade;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um post")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = PostResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public PostResponse create(@RequestBody @Valid @NotNull CreatePostRequest createPostRequest) {
        return postFacade.create(createPostRequest);
    }

    @GetMapping("/{page}/{size}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos os posts de forma paginada")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PostResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public Page<PostResponse> findAll(@PathVariable("page") @NotNull Integer page,
                                     @PathVariable("size") @NotNull Integer size) {
        return postFacade.findAll(page, size);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Deleta um post por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public void findAll(@PathVariable("id") @NotNull String id) {
        postFacade.deleteById(id);
    }
}