package br.com.pardalZ7.service_user.services.interfaces;

import br.com.pardalZ7.service_user.domain.DTO.UserApplicationDTO;

public interface UserApplicationServiceInterface {

    UserApplicationDTO findApplicationPermissionByUser(Long userId, Long applicationId);
    UserApplicationDTO allow(Long userId, Long applicationId);
    UserApplicationDTO revoke(Long userId, Long applicationId);

}
