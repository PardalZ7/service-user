package br.com.pardalZ7.service_user.services.interfaces;

import br.com.pardalZ7.service_user.domain.DTO.UserApplicationDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserApplicationServiceInterface {

    UserApplicationDTO findById(Long id);
    UserApplicationDTO findApplicationPermissionByUser(Long userId, Long applicationId);
    List<UserApplicationDTO> findAll(Pageable pageable);
    UserApplicationDTO create(UserApplicationDTO userDto);
    UserApplicationDTO update(UserApplicationDTO userDto);
    void deleteById(Long id);

}
