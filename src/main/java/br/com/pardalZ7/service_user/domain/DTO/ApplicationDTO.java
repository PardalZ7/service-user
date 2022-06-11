package br.com.pardalZ7.service_user.domain.DTO;

import br.com.pardalZ7.service_user.domain.base.BaseDTO;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApplicationDTO extends BaseDTO {

    private String name;
    private List<UserApplicationDTO> userApplications;

    @Builder
    public ApplicationDTO(Long id, Boolean enable, String name, List<UserApplicationDTO> userApplications) {
        super(id, enable);
        this.name = name;
        this.userApplications = userApplications;
    }

}
