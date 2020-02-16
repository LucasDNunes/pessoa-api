package br.com.pessoa.security.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = -6553955575034730263L;

    @Id
    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

}
