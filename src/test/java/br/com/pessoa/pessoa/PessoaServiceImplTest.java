package br.com.pessoa.pessoa;

import br.com.pessoa.pessoa.exception.CpfInvalidoException;
import br.com.pessoa.pessoa.exception.EmailInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@SpringBootTest
class PessoaServiceImplTest {

  @Autowired
  private PessoaService pessoaService;


  @Test
  @DisplayName("Listar todas pessoas")
  void deve_listarTasPessoas_quando_tiverRegistrosNoBanco() {
    Page<Pessoa> pessoas = pessoaService.listarTodos(PageRequest.of(0, 30));
    assertFalse(pessoas.isEmpty(), "a lista não deve estar fazia pois tem pessoa cadastrada");
  }

  @Test
  @DisplayName("Buscar pessoa por id")
  void deve_buscarPessoaPorId_quando_existirOIdCadastrado() {
    Page<Pessoa> pessoas = pessoaService.listarTodos(PageRequest.of(0, 30));
    Long id = pessoas.getContent().get(0).getId();
    Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);

    assertFalse(pessoa.isEmpty(), "Deve econtrar a pessoa por id");
    assertEquals("id deve ser o mesmo da busca", id, pessoa.get().getId());
  }

  @Test
  @DisplayName("Salvar uma nova pessoa")
  void deve_salvarPessoa_quando_semErrosNoCadastro() {
    Pessoa pessoa = Pessoa.builder()
            .nome("usuário teste")
            .email("teste@teste.com")
            .cpf("204.211.020-55") // cpf gerado em https://www.4devs.com.br/gerador_de_cpf
            .naturalidade("cidade teste")
            .nascionalidade("país teste")
            .sexo(Sexo.MASCULINO)
            .dataNascimento(LocalDate.now().minusYears(1))
            .build();

    pessoa = pessoaService.salvar(pessoa);

    assertNotNull(pessoa.getId(), "deve registrar o id da pessoa nova");
  }

  @Test
  @DisplayName("Erro salvar uma nova pessoa")
  void deve_erroAoSalvarPessoa_quando_emailInvalido() {
    Pessoa pessoa = Pessoa.builder()
            .nome("usuário teste")
            .email("teste@teste")
            .cpf("204.211.020-55")
            .naturalidade("cidade teste")
            .nascionalidade("país teste")
            .sexo(Sexo.MASCULINO)
            .dataNascimento(LocalDate.now().minusYears(1))
            .build();

    Assertions.assertThrows(EmailInvalidoException.class, () -> pessoaService.salvar(pessoa), "deve dar erro pois o email é inválido");
  }

  @Test
  @DisplayName("Erro salvar uma nova pessoa")
  void deve_erroAoSalvarPessoa_quando_cpfInvalido() {
    Pessoa pessoa = Pessoa.builder()
            .nome("usuário teste")
            .email("teste@teste.com")
            .cpf("111.111.111-11")
            .naturalidade("cidade teste")
            .nascionalidade("país teste")
            .sexo(Sexo.MASCULINO)
            .dataNascimento(LocalDate.now().minusYears(1))
            .build();

    Assertions.assertThrows(CpfInvalidoException.class, () -> pessoaService.salvar(pessoa), "deve dar erro pois o CPF é inválido");
  }

  @Test
  @DisplayName("excuir pessoa")
  void deve_excluirPessoa_quando_existirNaBase() {
      Pessoa pessoa = Pessoa.builder()
              .nome("usuário teste")
              .email("teste@teste.com")
              .cpf("807.632.820-90") // cpf gerado em https://www.4devs.com.br/gerador_de_cpf
              .naturalidade("cidade teste")
              .nascionalidade("país teste")
              .sexo(Sexo.MASCULINO)
              .dataNascimento(LocalDate.now().minusYears(1))
              .build();

      pessoa = pessoaService.salvar(pessoa);

      pessoaService.excluir(pessoa.getId());

      assertTrue("não deve conter a pessoa pois acabou de ser excluido", pessoaService.buscarPorId(pessoa.getId()).isEmpty());

  }

  @Test
  @DisplayName("atualizar pessoa")
  void deve_atualizarPessoa() {
    Pessoa pessoa = Pessoa.builder()
            .nome("usuário teste")
            .email("teste@teste.com")
            .cpf("491.245.960-78") // cpf gerado em https://www.4devs.com.br/gerador_de_cpf
            .naturalidade("cidade teste")
            .nascionalidade("país teste")
            .sexo(Sexo.MASCULINO)
            .dataNascimento(LocalDate.now().minusYears(1))
            .build();

    pessoa = pessoaService.salvar(pessoa);
    String novoNome = "Novo nome";
    pessoa.setNome(novoNome);

    pessoaService.atualizar(pessoa, pessoa.getId());
    pessoa = pessoaService.buscarPorId(pessoa.getId()).get();
    assertEquals("Deve ter sido atualizado o para o novo nome", novoNome, pessoa.getNome());
  }

}