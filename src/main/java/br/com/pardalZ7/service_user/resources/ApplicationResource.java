package br.com.pardalZ7.service_user.resources;

import br.com.pardalZ7.service_user.domain.DTO.ApplicationDTO;
import br.com.pardalZ7.service_user.services.impl.ApplicationServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RequestMapping(value = "/application")
@RestController
public class ApplicationResource {

    public static final String ID = "/{id}";
    public static final String NAME = "/register/{name}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ApplicationServiceImpl service;

    @GetMapping(value = ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), ApplicationDTO.class));
    }

    @GetMapping()
    public ResponseEntity<List<ApplicationDTO>> findAll(@RequestParam(required=false) Integer page,
                                                 @RequestParam(required=false) Integer pageSize,
                                                 @RequestParam(required=false) Boolean showAll
                                                ) {

        if (showAll == null)
            showAll = false;

        if ((page == null) || (page < 0))
            page = 0;

        if ((pageSize == null) || (pageSize < 1))
            pageSize = 100;

        Pageable pagination = PageRequest.of(page, pageSize);

        List<ApplicationDTO> applicationDto = service.findAll(pagination, showAll);
        return ResponseEntity.ok().body(applicationDto);
    }

    @PostMapping(value = NAME)
    public ResponseEntity<String> register(@PathVariable(required=true) String name) {
        String hashCode = service.register(name);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("HASH").buildAndExpand(hashCode).toUri();
        return ResponseEntity.created(uri).body(hashCode);
    }

    @PutMapping(value = ID)
    public ResponseEntity<ApplicationDTO> update(@PathVariable Long id, @RequestBody @NotNull ApplicationDTO applicationBody){
        applicationBody.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(applicationBody), ApplicationDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
