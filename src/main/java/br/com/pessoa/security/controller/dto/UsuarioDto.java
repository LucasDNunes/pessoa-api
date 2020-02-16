package br.com.pessoa.security.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private String username;
    private String password;
}
