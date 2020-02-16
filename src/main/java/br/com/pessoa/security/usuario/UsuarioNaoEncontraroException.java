package br.com.pessoa.security.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontraroException extends RuntimeException {

    public UsuarioNaoEncontraroException() {
        super("Usuário ou senha incorreto");
    }
}
