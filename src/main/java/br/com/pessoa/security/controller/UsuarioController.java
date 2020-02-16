package br.com.pessoa.security.controller;

import br.com.pessoa.security.controller.dto.UsuarioDto;
import br.com.pessoa.security.usuario.UsuarioMapper;
import br.com.pessoa.security.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.service = usuarioService;
    }

    @PostMapping
    public boolean login(@RequestBody UsuarioDto usuarioDto) {
        return service.login(UsuarioMapper.toEntity(usuarioDto));
    }

}
