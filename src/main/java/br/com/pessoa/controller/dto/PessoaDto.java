package br.com.pessoa.controller.dto;

import br.com.pessoa.pessoa.Sexo;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PessoaDto implements Serializable {

    private static final long serialVersionUID = 676462248812164853L;

    private Long id;
    private String nome;
    private Sexo sexo;
    private String email;
    private String dataNascimento;
    private String naturalidade;
    private String nascionalidade;
    private String cpf;
}
