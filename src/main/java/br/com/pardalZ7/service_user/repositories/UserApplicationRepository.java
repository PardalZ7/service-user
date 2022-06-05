package br.com.pardalZ7.service_user.repositories;

import br.com.pardalZ7.service_user.domain.entities.UserApplicationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserApplicationRepository extends PagingAndSortingRepository<UserApplicationEntity, Long> {
    @Query(value = " SELECT * FROM TB_UserApplications userApp WHERE userApp.userId=?1 AND userApp.applicationId=?2 ",
            nativeQuery = true)
    Optional<UserApplicationEntity> findApplicationPermissionByUser(Long userId, Long applicationId);
}
