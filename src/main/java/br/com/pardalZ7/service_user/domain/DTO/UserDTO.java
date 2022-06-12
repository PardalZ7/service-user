package br.com.pardalZ7.service_user.domain.DTO;

import br.com.pardalZ7.service_user.domain.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO extends BaseDTO {

    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;
    private List<UserApplicationDTO> userApplications;

    @Builder
    public UserDTO(Long id, Boolean enable, String name, String email, String pass,
                   List<UserApplicationDTO> userApplications) {
        super(id, enable);
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.userApplications = userApplications;
    }
}
