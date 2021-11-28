package com.fernando.socialmediaservice.post.controller;

import com.fernando.socialmediaservice.exception.BadRequestExceptionDetails;
import com.fernando.socialmediaservice.exception.InternalServerErrorExceptionDetails;
import com.fernando.socialmediaservice.post.facade.PostFacade;
import com.fernando.socialmediaservice.post.model.request.CreatePostRequest;
import com.fernando.socialmediaservice.post.model.request.UpdatePostRequest;
import com.fernando.socialmediaservice.post.model.response.PostResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualiza um post")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PostResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public PostResponse update(@RequestBody @Valid @NotNull UpdatePostRequest updatePostRequest) {
        return postFacade.update(updatePostRequest);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos os posts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PostResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public List<PostResponse> findAll() {
        return postFacade.findAll();
    }

    @GetMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos os posts pelo ID do author de forma paginada")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PostResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public Page<PostResponse> findAllByAuthorId(@RequestParam @NotNull String authorId,
                                                @RequestParam @NotNull Integer page,
                                                @RequestParam @NotNull Integer size) {
        return postFacade.findAllByAuthorId(authorId, page, size);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deleta um post por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public void deleteById(@RequestParam @NotBlank String userId, @RequestParam @NotBlank String postId) {
        postFacade.deleteById(userId, postId);
    }
}
