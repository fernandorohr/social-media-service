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
public class CommentRequest {

    @NotBlank(message = "O nome do autor não pode ser vazio.")
    @ApiModelProperty(value = "Nome do autor do comentário", example = "Alison", required = true)
    private String author;
    @NotBlank(message = "O ID do autor não pode ser vazio.")
    @ApiModelProperty(value = "ID do autor do comentário", example = "619ffd6ee93b7f4b3078e40a", required = true)
    private String authorId;
    @NotBlank(message = "O comentário em si não pode ser vazio.")
    @ApiModelProperty(value = "Comentário em si", example = "Muito bacana isso", required = true)
    private String body;
}
