package br.com.pardalZ7.service_user.domain.DTO;

import br.com.pardalZ7.service_user.domain.base.BaseEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ApplicationEntity extends BaseEntity {

    protected Long id;
    private String name;
    private List<UserApplicationEntity> userApplications;

}
