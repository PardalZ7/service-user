package br.com.pardalZ7.service_user.repositories;

import br.com.pardalZ7.service_user.domain.entities.UserApplicationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserApplicationRepository extends PagingAndSortingRepository<UserApplicationEntity, Long> {
    @Query(value = " SELECT * FROM tb_user_applications userApp WHERE userApp.user_id=?1 AND userApp.application_id=?2 ",
            nativeQuery = true)
    Optional<UserApplicationEntity> findApplicationPermissionByUser(Long userId, Long applicationId);
}
