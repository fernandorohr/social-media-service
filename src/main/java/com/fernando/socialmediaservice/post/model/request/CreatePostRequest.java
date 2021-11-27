package com.fernando.socialmediaservice.post.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequest {

    @NotBlank(message = "O autor do post não pode ser nulo")
    @ApiModelProperty(value = "Nome do autor do post", example = "Fernando", required = true)
    private String author;
    @ApiModelProperty(value = "Id do autor do post", example = "619ffd6ee93b7f4b3078e40a", required = true)
    @NotBlank(message = "O ID do autor do post não pode ser nulo")
    private String authorId;
    @ApiModelProperty(value = "Título do post", example = "Um Título", required = true)
    @NotBlank(message = "O título do post não pode ser nulo")
    private String title;
    @ApiModelProperty(value = "Corpo do post", example = "Um texto qualquer do Post")
    private String body;
}
