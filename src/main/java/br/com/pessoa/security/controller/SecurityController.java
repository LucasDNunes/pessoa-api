package br.com.pessoa.security.controller;

import br.com.pessoa.security.usuario.Usuario;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class SecurityController {

    @GetMapping(value = "/user-auth")
    @ResponseBody
    @Secured({"ROLE_CLIENT"})
    public Usuario usuario(){
        return (Usuario) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
