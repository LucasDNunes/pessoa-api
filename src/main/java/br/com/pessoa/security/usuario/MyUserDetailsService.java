package br.com.pessoa.security.usuario;

import br.com.pessoa.exception.RegistroNaoEncontradoException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

//@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public MyUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorId(username).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário", username));

//        return new UserRepositoryUserDetails(usuario.getUsername(), usuario.getPassword(), usuario.getRoles(), usuario.getCreatedDate(), usuario.getModifiedDate());
        return null;
    }

    private final static class UserRepositoryUserDetails extends Usuario implements UserDetails{

        private static final long serialVersionUID = 1L;

//        public UserRepositoryUserDetails(String username, String password, List<Role> roles, LocalDateTime createdDate, LocalDateTime modifiedDate) {
//            super(username, password, roles, createdDate, modifiedDate);
//        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return getRoles();
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return Boolean.TRUE;
        }

        @Override
        public boolean isAccountNonLocked() {
            return Boolean.TRUE;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return Boolean.TRUE;
        }

        @Override
        public boolean isEnabled() {
            return Boolean.TRUE;
        }
    }
}
