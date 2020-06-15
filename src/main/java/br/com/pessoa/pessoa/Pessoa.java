package br.com.pessoa.pessoa;

import br.com.pessoa.core.EntityCreate;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa extends EntityCreate implements Serializable {
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

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "telefone")
    private Integer telefone;

}
