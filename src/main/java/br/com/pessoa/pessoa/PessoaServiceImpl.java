package br.com.pessoa.pessoa;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.pessoa.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PessoaServiceImpl implements PessoaService {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

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
        if (!validarEmail(pessoa.getEmail())) {
            throw new IllegalArgumentException("Email inválido");
        }
        String cpfApenasNumero = pessoa.getCpf().replaceAll("[^0-9]", "");
        if (!validarCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }
        pessoa.setCpf(cpfApenasNumero);
        return repo.save(pessoa);
    }

    private boolean validarEmail(String email) {
        if (Objects.nonNull(email)) {
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return true;
    }

    private boolean validarCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void excluir(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Pessoa> buscarPorId(Long id) {
        return repo.findById(id);
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
