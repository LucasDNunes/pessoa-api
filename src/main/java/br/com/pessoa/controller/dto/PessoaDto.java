package br.com.pessoa.controller.dto;

import br.com.pessoa.pessoa.Sexo;
import lombok.*;

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
    private String dataNascimento;
    private String naturalidade;
    private String cpf;
}
