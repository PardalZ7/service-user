package br.com.pardalZ7.service_user.repositories;

import br.com.pardalZ7.service_user.domain.entities.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {

}
