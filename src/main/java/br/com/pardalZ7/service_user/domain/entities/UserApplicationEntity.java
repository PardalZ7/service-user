package br.com.pardalZ7.service_user.domain.entities;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TB_UserApplications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserApplicationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "applicationId")
    private ApplicationEntity application;

    @UpdateTimestamp
    protected LocalDateTime lastLogin;

    @Column(name = "allowed", columnDefinition = "boolean default false")
    protected Boolean allowed;


}
