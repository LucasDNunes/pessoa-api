package br.com.pessoa.pessoa;

import br.com.pessoa.controller.PessoaDto;
import br.com.pessoa.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repo;

    @Autowired
    public PessoaServiceImpl(PessoaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Pessoa> listarTodos(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        return repo.save(pessoa);
    }

    @Override
    public void excluir(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Pessoa> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Pessoa atualizar(Pessoa pessoa, Long id) {
        if (repo.existsById(id)) {
            pessoa.setId(id);
            return repo.save(pessoa);
        }
         throw new RegistroNaoEncontradoException(Pessoa.class.getSimpleName(), id);
    }
}
