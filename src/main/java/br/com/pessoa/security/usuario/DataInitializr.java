package br.com.pessoa.security.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        List<Usuario> users = usuarioRepository.findAll();

        if (users.isEmpty()) {
            createUser("guest1", "guest1", "");
            createUser("Admin", passwordEncoder.encode("123456"), "ROLE_ADMIN");
            createUser("Cliente", passwordEncoder.encode("123456"), "ROLE_CLIENT");
        }

    }

    public void createUser(String name, String password, String roleName) {

        Role role = Role.builder().name(roleName).build();

//        this.roleRepository.save(role);
        Usuario user = Usuario.builder()
                .username(name)
                .password(password)
                .build();
        usuarioRepository.save(user);
    }
}
