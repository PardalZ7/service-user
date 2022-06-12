package br.com.pardalZ7.service_user.resources;

import br.com.pardalZ7.service_user.domain.DTO.UserDTO;
import br.com.pardalZ7.service_user.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RequestMapping(value = "/user")
@RestController
public class UserResource {

    public static final String ID = "/{id}";

    @Autowired
    private UserServiceImpl service;

    @GetMapping(value = ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(@RequestParam(required=false) Integer page,
                                                 @RequestParam(required=false) Integer pageSize,
                                                 @RequestParam(required=false) Boolean showAll
                                                ) {

        if (showAll == null)
            showAll = false;

        if ((page == null) || (page < 0))
            page = 0;

        if ((pageSize == null) || (pageSize < 1))
            pageSize = 100;

        return ResponseEntity.ok().body(service.findAll(PageRequest.of(page, pageSize), showAll));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userBody) {
        UserDTO newUser = service.create(userBody);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ID).buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @NotNull UserDTO userBody){
        userBody.setId(id);
        return ResponseEntity.ok().body(service.update(userBody));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
