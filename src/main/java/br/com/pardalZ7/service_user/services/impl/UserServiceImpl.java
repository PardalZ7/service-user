package br.com.pardalZ7.service_user.services.impl;

import br.com.pardalZ7.service_user.domain.DTO.UserDTO;
import br.com.pardalZ7.service_user.domain.entities.UserEntity;
import br.com.pardalZ7.service_user.repositories.UserRepository;
import br.com.pardalZ7.service_user.services.exceptions.DataIntegrityViolationException;
import br.com.pardalZ7.service_user.services.exceptions.ObjectNotFoundException;
import br.com.pardalZ7.service_user.services.interfaces.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO create(UserDTO userDTO) {
        this.findByEmail(userDTO);
        return mapper.map(repository.save(mapper.map(userDTO, UserEntity.class)), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        if (!user.isPresent())
            throw new ObjectNotFoundException("User not found");
        return mapper.map(user.get(), UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(UserDTO userDTO) {
        Optional<UserEntity> user = repository.findByEmail(userDTO.getEmail());
        if (user.isPresent() && !user.get().getId().equals(userDTO.getId()))
            throw new DataIntegrityViolationException("Email already registered");
        return mapper.map(user.get(), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream().map(
                userEntity -> mapper.map(userEntity, UserDTO.class)).toList();
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        this.findById(userDTO.getId());
        this.findByEmail(userDTO);
        return mapper.map(repository.save(mapper.map(userDTO, UserEntity.class)), UserDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        UserDTO user = this.findById(id);
        user.setEnable(false);
        repository.save(mapper.map(user, UserEntity.class));
    }
}
