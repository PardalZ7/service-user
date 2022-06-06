package br.com.pardalZ7.service_user.domain.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ApplicationDTO {

    protected Long id;
    protected Boolean enable;

    private String name;
    private List<UserApplicationDTO> userApplications;

}
