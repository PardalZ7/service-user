package br.com.pardalZ7.service_user.domain.base;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    protected Boolean deleted = false;

    @Column(name = "createdAt", updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    protected LocalDateTime updatedAt;

}

