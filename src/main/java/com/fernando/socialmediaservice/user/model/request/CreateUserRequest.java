package com.fernando.socialmediaservice.user.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank(message = "O nome não pode estar vazio")
    @ApiModelProperty(value = "Nome do usuário", example = "Eliton", required = true)
    private String name;
    @NotBlank(message = "O email não pode estar vazio")
    @ApiModelProperty(value = "Email do usuário", example = "eliton@eliton.com", required = true)
    @Pattern(regexp = "^(\\w)[\\w.]*@\\w+(\\.\\w+){1,2}", message = "Email inválido")
    private String email;
    @NotBlank(message = "A senha não pode estar vazia")
    @ApiModelProperty(value = "Senha do usuário", example = "*****", required = true)
    private String password;
}
