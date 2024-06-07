package com.example.azulano.controller.Habitat;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.azulano.dto.Habitat.HabitatDTO;
import com.example.azulano.model.Habitat.Habitat;
import com.example.azulano.repository.Habitat.HabitatRepository;
import com.example.azulano.service.Habitat.HabitatService;
import org.springframework.web.bind.annotation.RequestBody;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "habitat")
@RestController
@Slf4j
@RequestMapping("/habitat")
@Tag(name = "Habitats")
public class HabitatController {
   
    @Autowired
    HabitatService service;


    @Autowired
    HabitatRepository repository;

     @Cacheable
    @GetMapping("/{id}")
     @Operation(
        summary = "Listar Habitat por Id",
        description = "Retorna um Habitat por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Habitat retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public EntityModel<Habitat> searchById(@PathVariable Long id){
        log.info("Buscando animal com o id {}", id);
        return service.searchById(id);
    }

     @Operation(
        summary = "Listar Habitats de animais",
        description = "Retorna uma paginação de Habitats"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Habitats retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    @GetMapping()
     public PagedModel<EntityModel<Habitat>> findByPages(
        @RequestParam(required = false) String nameHabitat,
        @PageableDefault(size = 5,direction = Direction.DESC) Pageable pageable
    ){
        log.info("Paginação");
        return service.findByPages(nameHabitat,pageable);
    }

    @Operation(
        summary = "Criar Habitat",
        description = "Cria um habitat com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Habitat criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<EntityModel<Habitat>> created(@RequestBody @Valid HabitatDTO data){
        return service.created(data);
    }

    @Operation(
        summary = "Atualizar Habitat",
        description = "Atualiza habitat de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Habitat atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Habitat>> put(@RequestBody @Valid HabitatDTO data,@PathVariable Long id){
        return service.put(id, data);
    }

     @Operation(
        summary = "Deletar Habitat",
        description = "Deleta Habitat com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Habitat deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> destroy(@PathVariable Long id){
        return service.destroy(id);

}

}
