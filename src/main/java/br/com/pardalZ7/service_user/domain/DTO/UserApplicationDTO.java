package br.com.pardalZ7.service_user.domain.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserApplicationDTO {

    protected Long id;
    protected LocalDateTime lastLogin;
    protected Boolean allowed;

}
