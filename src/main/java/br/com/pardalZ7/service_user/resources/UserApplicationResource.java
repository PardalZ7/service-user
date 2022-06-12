package br.com.pardalZ7.service_user.resources;

import br.com.pardalZ7.service_user.domain.DTO.UserApplicationDTO;
import br.com.pardalZ7.service_user.services.impl.UserApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/permission")
@RestController
public class UserApplicationResource {

    public static final String ALLOW = "/allow";
    public static final String REVOKE = "/revoke";
    public static final String VIEW = "/viewPermission";

    @Autowired
    private UserApplicationServiceImpl service;

    @GetMapping(value = VIEW, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserApplicationDTO> viewPermission(@RequestHeader Long userId,
                                                             @RequestHeader Long applicationId) {
        return ResponseEntity.ok().body(service.findApplicationPermissionByUser(userId, applicationId));
    }

    @PostMapping(value = ALLOW)
    public ResponseEntity<UserApplicationDTO> allow(@RequestHeader Long userId,
                                        @RequestHeader Long applicationId) {
        return ResponseEntity.ok().body(service.allow(userId, applicationId));
    }

    @PostMapping(value = REVOKE)
    public ResponseEntity<UserApplicationDTO> revoke(@RequestHeader Long userId,
                                         @RequestHeader Long applicationId) {
        return ResponseEntity.ok().body(service.revoke(userId, applicationId));
    }

}
