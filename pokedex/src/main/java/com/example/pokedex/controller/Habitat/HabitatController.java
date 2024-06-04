package com.example.pokedex.controller.Habitat;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.dto.Habitat.HabitatDTO;
import com.example.pokedex.model.Animals.Animals;
import com.example.pokedex.model.Habitat.Habitat;
import com.example.pokedex.service.Habitat.HabitatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "animals")
@RestController
@Slf4j
@RequestMapping("/habitat")
@Tag(name = "Habitats")
public class HabitatController {
   
    @Autowired
    HabitatService service;

     @Cacheable
    @GetMapping("/{id}")
     @Operation(
        summary = "Listar Animais por Id",
        description = "Retorna um animal por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Animal retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public EntityModel<Habitat> searchById(@PathVariable Long id){
        log.info("Buscando animal com o id {}", id);
        return service.searchById(id);
    }

     @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<EntityModel<Habitat>> created(@RequestBody @Valid HabitatDTO data){
       return service.created(data);
    }
}
