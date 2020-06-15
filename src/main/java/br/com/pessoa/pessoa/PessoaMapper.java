package br.com.pessoa.pessoa;

import br.com.pessoa.controller.dto.PessoaDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PessoaMapper {

    private static final String FORMAT = "dd/MM/yyyy";

    public static PessoaDto toDto(Pessoa pessoa) {
        return PessoaDto.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .email(pessoa.getEmail())
                .naturalidade(pessoa.getNaturalidade())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento().format(DateTimeFormatter.ofPattern(FORMAT)))
                .build();
    }

    public static Pessoa toEntity(PessoaDto pessoaDto) {
        Pessoa pessoa = Pessoa.builder()
                .id(pessoaDto.getId())
                .nome(pessoaDto.getNome())
                .sexo(pessoaDto.getSexo())
                .email(pessoaDto.getEmail())
                .naturalidade(pessoaDto.getNaturalidade())
                .cpf(pessoaDto.getCpf())
                .build();

        if (!dataValida(pessoaDto.getDataNascimento())) {
            throw new IllegalArgumentException("Data inválida");
        }
         pessoa.setDataNascimento(LocalDate.parse(pessoaDto.getDataNascimento(), DateTimeFormatter.ofPattern(FORMAT)));
        return pessoa;
    }

    private static boolean dataValida(String dataNascimento) {
        Objects.requireNonNull(dataNascimento, "Data nascimento não pode ser nula");

        try {
            LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern(FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
