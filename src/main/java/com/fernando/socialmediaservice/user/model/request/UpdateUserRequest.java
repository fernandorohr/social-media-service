package com.fernando.socialmediaservice.user.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    @ApiModelProperty(value = "Nome do usuário", example = "Eliton")
    private String name;
    @ApiModelProperty(value = "Email do usuário", example = "eliton@eliton.com")
    @Pattern(regexp = "^(\\w)[\\w.]*@\\w+(\\.\\w+){1,2}", message = "Email inválido")
    private String email;
    @ApiModelProperty(value = "Senha do usuário", example = "*****")
    private String password;
}
