package br.com.pardalZ7.service_user.services.interfaces;

import br.com.pardalZ7.service_user.domain.DTO.ApplicationDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationServiceInterface {

    ApplicationDTO findById(Long id);
    List<ApplicationDTO> findAll(Pageable pageable);
    ApplicationDTO create(ApplicationDTO userDto);
    ApplicationDTO update(ApplicationDTO userDto);
    void deleteById(Long id);

}
