package com.example.pokedex.controller.Animals;

import static org.springframework.http.HttpStatus.CREATED;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.model.Animals.Animals;
import com.example.pokedex.service.AnimalsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Controller
@Slf4j
@RequestMapping("/animals")
@Tag(name = "Animals")
public class AnimalsController {
    
    @Autowired
    AnimalsService service;

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
    public EntityModel<Animals> searchById(@PathVariable Long id){
        log.info("Buscando animal com o id {}", id);
        return service.searchById(id);
    }

    @Operation(
        summary = "Listar paginação de animais",
        description = "Retorna uma paginação de animais"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Animais retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    @GetMapping()
     public PagedModel<EntityModel<Animals>> findByPages(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String species,
        @PageableDefault(size = 5,direction = Direction.DESC) Pageable pageable
    ){
        log.info("Paginação");
        return service.findByPages(name, species, pageable);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<EntityModel<Animals>> created(@RequestParam @Valid Animals data){
        return service.created(data);
    }
}
