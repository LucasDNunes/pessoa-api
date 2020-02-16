package br.com.pessoa.security.usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> buscarPorId(String username);

    boolean login(Usuario usuario);
}
