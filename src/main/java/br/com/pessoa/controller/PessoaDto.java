package br.com.pessoa.controller;

import br.com.pessoa.pessoa.Sexo;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PessoaDto {

    private Long id;
    private String nome;
    private Sexo sexo;
    private String email;
    private LocalDate dataNascimento;
    private String naturalidade;
    private String cpf;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
