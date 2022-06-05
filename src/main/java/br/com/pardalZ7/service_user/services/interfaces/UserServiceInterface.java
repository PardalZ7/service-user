package br.com.pardalZ7.service_user.services.interfaces;

import br.com.pardalZ7.service_user.domain.DTO.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserServiceInterface {

    UserDTO findById(Long id);
    UserDTO findByEmail(UserDTO userDTO);
    List<UserDTO> findAll(Pageable pageable);
    UserDTO create(UserDTO userDto);
    UserDTO update(UserDTO userDto);
    void deleteById(Long id);

}
