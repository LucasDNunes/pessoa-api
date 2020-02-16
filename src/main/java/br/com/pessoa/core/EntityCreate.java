package br.com.pessoa.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class EntityCreate {

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
