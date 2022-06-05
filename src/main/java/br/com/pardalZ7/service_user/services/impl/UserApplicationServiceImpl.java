package br.com.pardalZ7.service_user.services.impl;

import br.com.pardalZ7.service_user.domain.DTO.UserApplicationDTO;
import br.com.pardalZ7.service_user.domain.entities.UserApplicationEntity;
import br.com.pardalZ7.service_user.repositories.UserApplicationRepository;
import br.com.pardalZ7.service_user.services.exceptions.ObjectNotFoundException;
import br.com.pardalZ7.service_user.services.interfaces.UserApplicationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationServiceInterface {

    @Autowired
    private UserApplicationRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserApplicationDTO create(UserApplicationDTO userApplicationDTO) {
        return mapper.map(repository.save(mapper.map(userApplicationDTO, UserApplicationEntity.class)), UserApplicationDTO.class);
    }

    @Override
    public UserApplicationDTO findById(Long id) {
        Optional<UserApplicationEntity> userApplication = repository.findById(id);
        if (!userApplication.isPresent())
            throw new ObjectNotFoundException("UserApplication not found");
        return mapper.map(userApplication.get(), UserApplicationDTO.class);
    }

    @Override
    public UserApplicationDTO findApplicationPermissionByUser(Long userId, Long applicationId) {
        Optional<UserApplicationEntity> userApplication = repository.findApplicationPermissionByUser(userId, applicationId);
        if (!userApplication.isPresent())
            throw new ObjectNotFoundException("UserApplication not found");
        return mapper.map(userApplication.get(), UserApplicationDTO.class);
    }

    @Override
    public List<UserApplicationDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream().map(
                applicationEntity -> mapper.map(applicationEntity, UserApplicationDTO.class)).toList();
    }

    @Override
    public UserApplicationDTO update(UserApplicationDTO userApplicationDTO) {
        this.findById(userApplicationDTO.getId());
        return mapper.map(repository.save(mapper.map(userApplicationDTO, UserApplicationEntity.class)), UserApplicationDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        UserApplicationDTO userApplication = this.findById(id);
        userApplication.setEnable(false);
        repository.save(mapper.map(userApplication, UserApplicationEntity.class));
    }
}
