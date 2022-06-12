package br.com.pardalZ7.service_user.domain.entities;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity(name = "TB_Users")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    private String name;

    @Column(unique = true)
    private String email;

    private String pass;

    @Builder
    public UserEntity(Long id, Boolean enable, LocalDateTime createdAt, LocalDateTime updatedAt, String name,
                      String email, String pass) {
        super(id, enable, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
}
