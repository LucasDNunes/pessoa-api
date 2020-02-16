package br.com.pessoa.security.usuario;

import br.com.pessoa.security.controller.dto.UsuarioDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDto usuarioDto) {
        return Usuario.builder()
                .username(usuarioDto.getUsername())
                .password(usuarioDto.getPassword())
                .build();
    }
}
