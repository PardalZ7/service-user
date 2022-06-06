package br.com.pardalZ7.service_user.repositories;

import br.com.pardalZ7.service_user.domain.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query(value = " SELECT * FROM TB_Users users WHERE users.enable=true ",
            nativeQuery = true)
    Page<UserEntity> findAllEnable(Pageable pageable);
}
