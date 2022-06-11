package br.com.pardalZ7.service_user.domain.entities;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "TB_Applications")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ApplicationEntity extends BaseEntity {

    private String name;
    private String appHashCode;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity=UserApplicationEntity.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicationId")
    private List<UserApplicationEntity> userApplications;

    @Builder
    public ApplicationEntity(Long id, Boolean enable, LocalDateTime createdAt, LocalDateTime updatedAt, String name,
                             String appHashCode, List<UserApplicationEntity> userApplications) {
        super(id, enable, createdAt, updatedAt);
        this.name = name;
        this.appHashCode = appHashCode;
        this.userApplications = userApplications;
    }
}
