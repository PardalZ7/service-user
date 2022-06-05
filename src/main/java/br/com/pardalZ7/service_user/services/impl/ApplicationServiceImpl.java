package br.com.pardalZ7.service_user.services.impl;

import br.com.pardalZ7.service_user.domain.DTO.ApplicationDTO;
import br.com.pardalZ7.service_user.domain.entities.ApplicationEntity;
import br.com.pardalZ7.service_user.repositories.ApplicationRepository;
import br.com.pardalZ7.service_user.services.exceptions.ObjectNotFoundException;
import br.com.pardalZ7.service_user.services.interfaces.ApplicationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationServiceInterface {

    @Autowired
    private ApplicationRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ApplicationDTO create(ApplicationDTO applicationDTO) {
        return mapper.map(repository.save(mapper.map(applicationDTO, ApplicationEntity.class)), ApplicationDTO.class);
    }

    @Override
    public ApplicationDTO findById(Long id) {
        Optional<ApplicationEntity> application = repository.findById(id);
        if (!application.isPresent())
            throw new ObjectNotFoundException("Application not found");
        return mapper.map(application.get(), ApplicationDTO.class);
    }

    @Override
    public List<ApplicationDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream().map(
                applicationEntity -> mapper.map(applicationEntity, ApplicationDTO.class)).toList();
    }

    @Override
    public ApplicationDTO update(ApplicationDTO applicationDTO) {
        this.findById(applicationDTO.getId());
        return mapper.map(repository.save(mapper.map(applicationDTO, ApplicationEntity.class)), ApplicationDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        ApplicationDTO application = this.findById(id);
        application.setEnable(false);
        repository.save(mapper.map(application, ApplicationEntity.class));
    }
}
