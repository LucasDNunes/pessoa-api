package br.com.pessoa.pessoa;

import br.com.pessoa.controller.PessoaDto;

public class PessoaMapper {

    public static PessoaDto toDto(Pessoa pessoa) {
        return PessoaDto.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .email(pessoa.getEmail())
                .naturalidade(pessoa.getNaturalidade())
                .cpf(pessoa.getCpf())
                .createdDate(pessoa.getCreatedDate())
                .dataNascimento(pessoa.getDataNascimento())
                .modifiedDate(pessoa.getModifiedDate())
                .build();
    }

    public static Pessoa toEntity(PessoaDto pessoa) {
        return Pessoa.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .email(pessoa.getEmail())
                .naturalidade(pessoa.getNaturalidade())
                .cpf(pessoa.getCpf())
                .createdDate(pessoa.getCreatedDate())
                .dataNascimento(pessoa.getDataNascimento())
                .modifiedDate(pessoa.getModifiedDate())
                .build();
    }
}
