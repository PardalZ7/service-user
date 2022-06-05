package br.com.pardalZ7.service_user.repositories;

import br.com.pardalZ7.service_user.domain.entities.ApplicationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationEntity, Long> {

}
