package br.com.pardalZ7.service_user.domain.entities;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "TB_Users")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    private String name;

    @Column(unique = true)
    private String email;

    private String pass;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity=UserApplicationEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId")
    private List<UserApplicationEntity> userApplications;

    @Builder
    public UserEntity(Long id, Boolean enable, LocalDateTime createdAt, LocalDateTime updatedAt, String name,
                      String email, String pass, List<UserApplicationEntity> userApplications) {
        super(id, enable, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.userApplications = userApplications;
    }
}
