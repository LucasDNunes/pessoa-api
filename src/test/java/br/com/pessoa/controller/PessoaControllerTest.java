package br.com.pessoa.controller;

import br.com.pessoa.controller.dto.PessoaDto;
import br.com.pessoa.pessoa.Pessoa;
import br.com.pessoa.pessoa.PessoaService;
import br.com.pessoa.pessoa.Sexo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PessoaControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;


  private PessoaService pessoaService;

  @Autowired
  private PessoaController pessoaController;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    this.pessoaService = Mockito.mock(PessoaService.class);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    Whitebox.setInternalState(pessoaController, pessoaService);
  }


  @Test
  @DisplayName("listar todas Pessoas")
  void deve_acessarListarTodos() throws Exception {
    PageImpl<Pessoa> pessoaRetornar = new PageImpl<>(Collections.singletonList(Pessoa.builder()
            .id(1L)
            .nome("teste")
            .dataNascimento(LocalDate.now())
            .build()));
    when(pessoaService.listarTodos(any())).thenReturn(pessoaRetornar);
    this.mockMvc.perform(get("/pessoas"))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("buscar por id")
  void deve_acessarBuscarPorId() throws Exception {
    Optional<Pessoa> pessoaRetornar = Optional.of(Pessoa.builder()
            .id(1L)
            .nome("teste")
            .dataNascimento(LocalDate.now())
            .build());
    when(pessoaService.buscarPorId(any())).thenReturn(pessoaRetornar);
    this.mockMvc.perform(get("/pessoas/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("nova pessoa")
  void deve_acessarSalverPessoa() throws Exception {
    PessoaDto pessoaInserir = PessoaDto.builder()
            .nome("teste")
            .dataNascimento("11/07/2020")
            .naturalidade("teste")
            .nascionalidade("teste")
            .sexo(Sexo.FEMININO)
            .build();
    String jsonPessoa = objectMapper.writeValueAsString(pessoaInserir);
    when(pessoaService.salvar(any())).thenReturn(Pessoa.builder().id(21L).dataNascimento(LocalDate.now()).build());

    MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
    MockHttpServletRequestBuilder request = post("/pessoas");
    request.content(jsonPessoa);
    request.accept(MEDIA_TYPE_JSON_UTF8);
    request.contentType(MEDIA_TYPE_JSON_UTF8);
    mockMvc.perform(request)
            .andExpect(status().isOk());

  }

  @Test
  @DisplayName("deletar pessoa")
  void deve_acessarDeletePessoa() throws Exception {
    this.mockMvc.perform(delete("/pessoas/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("atualizar pessoa")
  void deve_acessarAtualizarPessoa() throws Exception {
    PessoaDto pessoaInserir = PessoaDto.builder()
            .nome("teste")
            .dataNascimento("11/07/2020")
            .naturalidade("teste")
            .nascionalidade("teste")
            .sexo(Sexo.FEMININO)
            .build();
    String jsonPessoa = objectMapper.writeValueAsString(pessoaInserir);
    when(pessoaService.atualizar(any(), anyLong())).thenReturn(Pessoa.builder().id(21L).dataNascimento(LocalDate.now()).build());
    MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
    MockHttpServletRequestBuilder request = patch("/pessoas/1");
    request.content(jsonPessoa);
    request.accept(MEDIA_TYPE_JSON_UTF8);
    request.contentType(MEDIA_TYPE_JSON_UTF8);
    mockMvc.perform(request)
            .andExpect(status().isOk());
  }

}