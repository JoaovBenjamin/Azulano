package com.example.azulano.controller.Animals;

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

import com.example.azulano.dto.Animals.AnimalsDTO;
import com.example.azulano.model.Animals.Animals;
import com.example.azulano.model.Habitat.Habitat;
import com.example.azulano.service.AnimalsService;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "animals")
@RestController
@Slf4j
@RequestMapping("/animals")
@Tag(name = "Animals")
public class AnimalsController {
    
    @Autowired
    AnimalsService service;

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
        @RequestParam(required = false) String family,
        @PageableDefault(size = 5,direction = Direction.DESC) Pageable pageable
    ){
        log.info("Paginação");
        return service.findByPages(name, family, pageable);
    }

    @Operation(
        summary = "Criar Animals",
        description = "Cria um animals com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Animal criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })

    @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<EntityModel<Animals>> created(@RequestBody @Valid AnimalsDTO data){
            return service.created(data);
    }

    @Operation(
        summary = "Atualizar Animal",
        description = "Atualiza animal de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Animal atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Animals>> put(@RequestBody @Valid AnimalsDTO data,@PathVariable Long id){
        return service.put(id, data);
    }

    @Operation(
        summary = "Deletar Animal",
        description = "Deleta Animal com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Animal deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> destroy(@PathVariable Long id){
        return service.destroy(id);
    }
}
