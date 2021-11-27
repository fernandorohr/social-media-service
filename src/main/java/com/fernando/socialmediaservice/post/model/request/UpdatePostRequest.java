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
public class UpdatePostRequest {

    @NotBlank(message = "O ID é obrigatório na atualização de post")
    @ApiModelProperty(value = "ID do post", example = "61a26bb57d65c61f3744d6a9", required = true)
    private String id;
    @ApiModelProperty(value = "Título do post", example = "Um Título")
    private String title;
    @ApiModelProperty(value = "Corpo do post", example = "Um texto qualquer do Post")
    private String body;
}
