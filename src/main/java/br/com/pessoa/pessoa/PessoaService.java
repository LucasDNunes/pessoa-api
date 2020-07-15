package br.com.pessoa.pessoa;

import br.com.pessoa.security.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PessoaService {


    Page<Pessoa> listarTodos(Pageable pageable);

    Pessoa salvar(Pessoa pessoa);

    void excluir(Long id);

    Optional<Pessoa> buscarPorId(Long id);

    Pessoa atualizar(Pessoa pessoa, Long id);
}
