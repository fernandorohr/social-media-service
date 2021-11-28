package com.fernando.socialmediaservice.post.controller;

import com.fernando.socialmediaservice.exception.BadRequestExceptionDetails;
import com.fernando.socialmediaservice.exception.InternalServerErrorExceptionDetails;
import com.fernando.socialmediaservice.post.facade.CommentFacade;
import com.fernando.socialmediaservice.post.model.request.CommentRequest;
import com.fernando.socialmediaservice.post.model.request.CreatePostRequest;
import com.fernando.socialmediaservice.post.model.response.PostResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;

    @PostMapping("/add/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um post")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED", response = PostResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = BadRequestExceptionDetails.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = InternalServerErrorExceptionDetails.class)
    })
    public PostResponse create(@RequestBody @Valid @NotNull CommentRequest commentRequest,
                               @PathVariable("postId") String postId) {
        return commentFacade.addComment(commentRequest, postId);
    }
}
