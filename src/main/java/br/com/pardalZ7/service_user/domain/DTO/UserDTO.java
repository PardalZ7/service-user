package br.com.pardalZ7.service_user.domain.DTO;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDTO extends BaseEntity {

    protected Long id;
    protected Boolean enable;

    private String name;
    private String email;
    private String pass;
    private List<UserApplicationDTO> userApplications;

}
