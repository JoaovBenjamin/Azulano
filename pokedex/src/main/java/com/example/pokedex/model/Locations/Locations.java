package com.example.pokedex.model.Locations;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.example.pokedex.controller.Locations.LocationsController;
import com.example.pokedex.dto.Locations.LocationsDTO;
import com.example.pokedex.model.Animals.Animals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Locations extends EntityModel<Locations> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    @NotBlank(message = "location.latiitude.notblank")
    private String latitude;
    @NotBlank(message = "location.longitude.notblank")
    private String longitude;
    @NotNull(message = "location.animal.notnull")
    @ManyToOne
    private Animals animal;

    public Locations(LocationsDTO data){
        this.latitude = data.latitude();
        this.animal = data.animals();
        this.longitude = data.longitude();
    }

     public EntityModel<Locations> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(LocationsController.class).searchById(id)).withSelfRel(),
            linkTo(methodOn(LocationsController.class).findByPages(null, null,null)).withRel("contents")        
        );
    }
}
