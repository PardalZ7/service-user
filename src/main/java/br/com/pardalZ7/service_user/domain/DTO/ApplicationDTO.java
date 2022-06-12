package br.com.pardalZ7.service_user.domain.DTO;

import br.com.pardalZ7.service_user.domain.base.BaseDTO;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApplicationDTO extends BaseDTO {

    private String name;

    @Builder
    public ApplicationDTO(Long id, Boolean enable, String name) {
        super(id, enable);
        this.name = name;
    }

}
