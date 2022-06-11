package br.com.pardalZ7.service_user.services.impl;

import br.com.pardalZ7.service_user.domain.DTO.ApplicationDTO;
import br.com.pardalZ7.service_user.domain.entities.ApplicationEntity;
import br.com.pardalZ7.service_user.repositories.ApplicationRepository;
import br.com.pardalZ7.service_user.services.exceptions.ObjectNotFoundException;
import br.com.pardalZ7.service_user.services.interfaces.ApplicationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ApplicationServiceImpl implements ApplicationServiceInterface {

    @Autowired
    private ApplicationRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ModelMapper skipNullMapper;

    @Override
    public ApplicationDTO create(ApplicationDTO applicationDTO) {
        return this.mapper.map(this.repository.save(
                this.mapper.map(applicationDTO, ApplicationEntity.class)), ApplicationDTO.class);
    }

    @Override
    public ApplicationDTO findById(Long id) {
        Optional<ApplicationEntity> application = this.repository.findById(id);
        if (!application.isPresent())
            throw new ObjectNotFoundException("Application not found");
        return this.mapper.map(application.get(), ApplicationDTO.class);
    }

    @Override
    public List<ApplicationDTO> findAll(Pageable pageable, Boolean showAll) {
        Page<ApplicationEntity> applicationEntities = null;
        if (showAll)
            applicationEntities = this.repository.findAll(pageable);
        else
            applicationEntities = this.repository.findAllEnable(pageable);
        return applicationEntities.stream().map(x -> mapper.map(x, ApplicationDTO.class)).toList();

    }

    @Override
    public ApplicationDTO update(ApplicationDTO applicationDTO) {
        ApplicationDTO applicationOnDB = this.findById(applicationDTO.getId());
        this.skipNullMapper.map(applicationDTO, applicationOnDB);
        return this.mapper.map(this.repository.save(this.mapper.map(applicationOnDB, ApplicationEntity.class)),
                ApplicationDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        ApplicationDTO application = this.findById(id);
        application.setEnable(false);
        this.repository.save(this.mapper.map(application, ApplicationEntity.class));
    }

    @Override
    public String register(String name) {

        ApplicationEntity application = ApplicationEntity.builder().name(name)
                .appHashCode(generateAppHash(56)).enable(true).build();

        this.repository.save(application);
        return  application.getAppHashCode();

    }

    private String generateAppHash(int size) {

        StringBuilder appHash = new StringBuilder();
        Random random = new Random();

        String setOfCharacters = "abcdefghijklmnopqrstuvxzABCDEFGHIJKLMNOPQRSTUVXZ1234567890!@#$%&*|";
        while (appHash.length() < size)
            appHash.append(setOfCharacters.charAt(random.nextInt(setOfCharacters.length())));

        return appHash.toString();

    }

}
