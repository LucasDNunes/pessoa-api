package br.com.pessoa.pessoa;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable {
    private static final long serialVersionUID = -4863565779850266829L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();


}
