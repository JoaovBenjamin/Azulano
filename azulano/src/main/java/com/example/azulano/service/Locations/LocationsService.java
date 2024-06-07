package com.example.azulano.service.Locations;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.azulano.dto.Locations.LocationsDTO;
import com.example.azulano.model.Locations.Locations;
import com.example.azulano.repository.Locations.LocationsRepository;


@Service
public class LocationsService {
    @Autowired
    LocationsRepository repository;

    @Autowired
    PagedResourcesAssembler<Locations> pageAssembler;


    public EntityModel<Locations> searchById(Long id){
        var locations = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Animal não encontrado")
        );

        return locations.toEntityModel();
    };

    public PagedModel<EntityModel<Locations>> findByPages(
        @RequestParam(required = false) String latitude,
        @RequestParam(required = false) String longitude,
        @PageableDefault(size = 5,direction = Direction.DESC) Pageable pageable
    ){
        Page<Locations> page = null;

        if(latitude != null && longitude != null){
            page = repository.findByLatitudeAndLongitude(latitude, longitude, pageable);
        }
        if(latitude != null){
            page = repository.findByLatitude(latitude, pageable);
        }
        if(longitude != null){
            page = repository.findByLongitude(longitude, pageable);
        }

        if(page == null){
            page = repository.findAll(pageable);
        }

        return pageAssembler.toModel(page, Locations::toEntityModel);
    }

    public ResponseEntity<EntityModel<Locations>> created(LocationsDTO data){
        Locations newLocations = new Locations(data);
        repository.save(newLocations);

        return ResponseEntity
        .created(newLocations.toEntityModel().getRequiredLink("self").toUri())
        .body(newLocations.toEntityModel());
        
    }

        public ResponseEntity<EntityModel<Locations>> put( Long id, LocationsDTO data) {
        verifyExistsId(id);
        Locations putLocations = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locations não encontrado"));
        BeanUtils.copyProperties(data, putLocations, "id");
        repository.save(putLocations);
        return ResponseEntity
                            .ok(putLocations.toEntityModel());
    }

    public ResponseEntity<Void> destroy(Long id){
       repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


     public void verifyExistsId(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
