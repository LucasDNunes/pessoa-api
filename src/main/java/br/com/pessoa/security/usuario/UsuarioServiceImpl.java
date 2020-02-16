package br.com.pessoa.security.usuario;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Usuario> buscarPorId(String username) {
        return repo.findById(username);
    }

    @Override
    public boolean login(Usuario usuario) {
        Usuario usuarioEncontrado = this.buscarPorId(usuario.getUsername()).orElseThrow(UsuarioNaoEncontraroException::new);
        if (usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
            return Boolean.TRUE;
        }
        throw new UsuarioNaoEncontraroException();
    }
}
