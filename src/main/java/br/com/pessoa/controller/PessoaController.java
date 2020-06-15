package br.com.pessoa.controller;

import br.com.pessoa.controller.dto.PessoaDto;
import br.com.pessoa.exception.RegistroNaoEncontradoException;
import br.com.pessoa.pessoa.Pessoa;
import br.com.pessoa.pessoa.PessoaMapper;
import br.com.pessoa.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

    public static final String ROLE_CLIENT = "ROLE_CLIENT";
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public Page<PessoaDto> listarTodas(@PageableDefault(size = 50) Pageable pageable) {
        return pessoaService.listarTodos(pageable).map(PessoaMapper::toDto);
    }

    @GetMapping(value = "/{id}")
    public PessoaDto buscarPorID(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPorId(id).orElseThrow(() -> new RegistroNaoEncontradoException(Pessoa.class.getSimpleName(), id));
        return PessoaMapper.toDto(pessoa);
    }

    @PostMapping
    public PessoaDto inserir(@RequestBody PessoaDto pessoaDto) {
        return PessoaMapper.toDto(pessoaService.salvar(PessoaMapper.toEntity(pessoaDto)));
    }

    @PatchMapping(value = "/{id}")
    public PessoaDto atualizar(@RequestBody PessoaDto pessoaDto, @PathVariable Long id) {
        return PessoaMapper.toDto(pessoaService.atualizar(PessoaMapper.toEntity(pessoaDto), id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        pessoaService.excluir(id);
    }
}
