package br.com.pardalZ7.service_user.domain.DTO;

import br.com.pardalZ7.service_user.domain.base.BaseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserApplicationDTO extends BaseDTO {

    protected LocalDateTime lastLogin;
    protected Boolean allowed;

    @Builder
    public UserApplicationDTO(Long id, Boolean enable, LocalDateTime lastLogin, Boolean allowed) {
        super(id, enable);
        this.lastLogin = lastLogin;
        this.allowed = allowed;
    }
}
