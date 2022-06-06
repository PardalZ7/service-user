package br.com.pardalZ7.service_user.repositories;

import br.com.pardalZ7.service_user.domain.entities.ApplicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationEntity, Long> {

    @Query(value = " SELECT * FROM TB_Applications users WHERE users.enable=true ",
            nativeQuery = true)
    Page<ApplicationEntity> findAllEnable(Pageable pageable);

}
