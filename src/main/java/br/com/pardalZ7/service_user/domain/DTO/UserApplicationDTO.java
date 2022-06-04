package br.com.pardalZ7.service_user.domain.DTO;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserApplicationDTO extends BaseEntity {

    protected Long id;
    private UserDTO user;
    private ApplicationDTO application;
    protected LocalDateTime lastLogin;
    protected Boolean allowed;

}
