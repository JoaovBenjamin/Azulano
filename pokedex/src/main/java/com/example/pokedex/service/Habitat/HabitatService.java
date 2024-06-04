package com.example.pokedex.service.Habitat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.pokedex.controller.Habitat.HabitatController;
import com.example.pokedex.dto.Habitat.HabitatDTO;
import com.example.pokedex.model.Animals.Animals;
import com.example.pokedex.model.Habitat.Habitat;
import com.example.pokedex.repository.Habitat.HabitatRepository;

@Service
public class HabitatService {
    @Autowired
    HabitatRepository repository;

    public EntityModel<Habitat> searchById(Long id){
        var habitat = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Animal não encontrado")
        );

        return habitat.toEntityModel();
    };


     public ResponseEntity<EntityModel<Habitat>> created(HabitatDTO data){
        Habitat newHabitat = new Habitat(data);
        newHabitat = repository.save(newHabitat);
        
        EntityModel<Habitat> entityModel = EntityModel.of(newHabitat);
        entityModel.add(linkTo(methodOn(HabitatController.class)).withRel("rel"));

        return ResponseEntity
            .created(entityModel.getRequiredLink("self").toUri())
            .body(entityModel);
    }
    }
    

