package br.com.pardalZ7.service_user.domain.entities;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TB_Users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserEntity extends BaseEntity {

    private String name;

    @Column(unique = true)
    private String email;

    private String pass;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity=UserApplicationEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId")
    private List<UserApplicationEntity> userApplications;

}