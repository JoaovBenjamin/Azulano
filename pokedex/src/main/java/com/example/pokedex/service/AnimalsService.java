package com.example.pokedex.service;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pokedex.dto.Animals.AnimalsDTO;
import com.example.pokedex.model.Animals.Animals;
import com.example.pokedex.repository.Animals.AnimalsRepository;

import jakarta.validation.Valid;


@Service
public class AnimalsService {
    @Autowired
    AnimalsRepository repository;

    @Autowired
    PagedResourcesAssembler<Animals> pageAssembler;


    public Animals saveAnimals(AnimalsDTO data) {
    Animals salvarAnimais = new Animals(data);
        return repository.save(salvarAnimais);
    }

    public EntityModel<Animals> searchById(@PathVariable Long id){
        var animal = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Animal não encontrado")
        );

        return animal.toEntityModel();
    };

    public PagedModel<EntityModel<Animals>> findByPages(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String species,
        @PageableDefault(size = 5,direction = Direction.DESC) Pageable pageable
    ){
        Page<Animals> page = null;

        if(name != null && species != null){
            page = repository.findByNameAndSpecies(name, species, pageable);
        }
        if(name != null){
            page = repository.findByName(name, pageable);
        }
        if(species != null){
            page = repository.findBySpecies(species, pageable);
        }

        if(page == null){
            page = repository.findAll(pageable);
        }

        return pageAssembler.toModel(page, Animals::toEntityModel);
    }

    public ResponseEntity<EntityModel<Animals>> created(@RequestParam @Valid Animals data){
        repository.save(data);

        return ResponseEntity
                         .created(data.toEntityModel().getRequiredLink("self").toUri())
                         .body(data.toEntityModel());
    }
}
