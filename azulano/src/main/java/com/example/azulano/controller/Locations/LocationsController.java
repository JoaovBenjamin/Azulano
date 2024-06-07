package com.example.azulano.controller.Locations;


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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.azulano.dto.Locations.LocationsDTO;
import com.example.azulano.model.Locations.Locations;
import com.example.azulano.service.Locations.LocationsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "locations")
@RestController
@Slf4j
@RequestMapping("/locations")
@Tag(name = "Locations")
public class LocationsController {
   
    @Autowired
    LocationsService service;


    

    @Cacheable
    @GetMapping("/{id}")
    @Operation(
        summary = "Listar Localização por Id",
        description = "Retorna uma Localização por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Localização retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public EntityModel<Locations> searchById(@PathVariable Long id){
        log.info("Buscando animal com o id {}", id);
        return service.searchById(id);
    }

     @Operation(
        summary = "Listar Locations de animais",
        description = "Retorna uma paginação de Locations"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Locations retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    @GetMapping()
     public PagedModel<EntityModel<Locations>> findByPages(
        @RequestParam(required = false) String latitude,
        @RequestParam(required = false) String longitude,
        @PageableDefault(size = 5,direction = Direction.DESC) Pageable pageable
    ){
        log.info("Paginação");
        return service.findByPages(latitude,longitude,pageable);
    }

    @Operation(
        summary = "Criar Locations",
        description = "Cria um Location com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Location criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<EntityModel<Locations>> created(@RequestBody @Valid LocationsDTO data){
        return service.created(data);
    }

    @Operation(
        summary = "Atualizar Locations",
        description = "Atualiza Locations de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Locations atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Locations>> put(@RequestBody @Valid LocationsDTO data,@PathVariable Long id){
        return service.put(id, data);
    }

     @Operation(
        summary = "Deletar Locations",
        description = "Deleta Locations com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Locations deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> destroy(@PathVariable Long id){
        return service.destroy(id);

}

}
