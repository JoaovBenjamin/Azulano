package com.example.pokedex.model.Habitat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.example.pokedex.controller.Habitat.HabitatController;
import com.example.pokedex.dto.Habitat.HabitatDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Habitat extends EntityModel<Habitat>{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "{habitat.typehabitat.notblank}")
    @Size(message = "{habitat.typehabitat.size}", min = 5)
    private String typeHabitat;
    @NotBlank(message = "{habitat.typehabitat.notblank}")
    @Size(message = "{habitat.typehabitat.size}", min = 5)
    private String nameHabitat;
    @NotBlank(message = "{habitat.temperatureWater.notblank}")
    @Size(message = "{habitat.temperatureWater.size}", min = 2)
    private String temperatureWater;
    @NotBlank(message = "{habitat.phWater.notblank}")
    @Size(message = "{habitat.phWater.size}", min = 2)
    private String phWater;

    

    public Habitat(HabitatDTO data){
        this.phWater = data.phWater();
        this.temperatureWater = data.temperatureWater();
        this.typeHabitat = data.typeHabitat();
        this.nameHabitat = data.nameHabitat();
        
    };



   
    public EntityModel<Habitat> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(HabitatController.class).searchById(id)).withSelfRel(),
            linkTo(methodOn(HabitatController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(HabitatController.class).findByPages(null, null)).withRel("contents")        
        );
    }

}

