package com.example.pokedex.model.Animals;

import org.springframework.hateoas.EntityModel;

import com.example.pokedex.controller.Animals.AnimalsController;
import com.example.pokedex.dto.Animals.AnimalsDTO;
import com.example.pokedex.service.AnimalsService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animals extends EntityModel<Animals>  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{animals.name.notblank}")
    @Size(min = 3, message = "{animals.name.size}")
    private String name;
    @NotBlank(message = "{animals.family.notblank}")
    @Size(message = "{animals.family.size}", min = 5)
    private String family;
    @NotBlank(message = "{animals.diet.notblank}")
    @Size(message = "{animals.diet.size}", min = 5)
    private String diet;
    @NotBlank(message = "{animals.species.notblank}")
    @Size(message = "{animals.species.size}", min = 5)
    private String species;    

    public Animals (AnimalsDTO data){
        this.diet = data.diet();
        this.family = data.family();
        this.name = data.name();
        this.species = data.species();
    }

    public EntityModel<Animals> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(AnimalsController.class).searchById(id)).withSelfRel(),
            linkTo(methodOn(AnimalsController.class).findByPages(null, null, null)).withRel("contents")
        
        );
    }
}
