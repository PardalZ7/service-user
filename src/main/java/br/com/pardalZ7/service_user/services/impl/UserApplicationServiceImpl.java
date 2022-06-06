package br.com.pardalZ7.service_user.services.impl;

import br.com.pardalZ7.service_user.domain.DTO.UserApplicationDTO;
import br.com.pardalZ7.service_user.domain.entities.UserApplicationEntity;
import br.com.pardalZ7.service_user.repositories.ApplicationRepository;
import br.com.pardalZ7.service_user.repositories.UserApplicationRepository;
import br.com.pardalZ7.service_user.repositories.UserRepository;
import br.com.pardalZ7.service_user.services.interfaces.UserApplicationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationServiceInterface {

    @Autowired
    private UserApplicationRepository repository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ModelMapper skipNullMapper;

    @Override
    public UserApplicationDTO allow(Long userId, Long applicationId) {
        return this.mapper.map(this.updateOrCreate(true, userId, applicationId), UserApplicationDTO.class);
    }

    @Override
    public UserApplicationDTO revoke(Long userId, Long applicationId) {
        return this.mapper.map(this.updateOrCreate(false, userId, applicationId), UserApplicationDTO.class);
    }

    @Override
    public UserApplicationDTO findApplicationPermissionByUser(Long userId, Long applicationId) {
        Optional<UserApplicationEntity> userApplication = this.repository.findApplicationPermissionByUser(userId, applicationId);
        if (!userApplication.isPresent())
            userApplication = Optional.of(UserApplicationEntity.builder().allowed(false).lastLogin(null).build());
        return this.mapper.map(userApplication.get(), UserApplicationDTO.class);
    }

    private UserApplicationEntity updateOrCreate(boolean allow, Long userId, Long applicationId) {
        Optional<UserApplicationEntity> userApplication = this.repository.findApplicationPermissionByUser(userId, applicationId);
        if (!userApplication.isPresent())
            return this.repository.save(UserApplicationEntity.builder()
                    .allowed(allow).lastLogin(LocalDateTime.now())
                    .application(this.applicationRepository.findById(applicationId).get())
                    .user(this.userRepository.findById(userId).get()).build());
        else {

            UserApplicationEntity data = userApplication.get();
            data.setAllowed(allow);
            return this.repository.save(data);

        }
    }

}
