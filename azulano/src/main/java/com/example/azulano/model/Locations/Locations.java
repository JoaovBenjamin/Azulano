package com.example.azulano.model.Locations;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.example.azulano.controller.Locations.LocationsController;
import com.example.azulano.dto.Locations.LocationsDTO;
import com.example.azulano.model.Animals.Animals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "T_AZL_LOCATIONS ")
public class Locations extends EntityModel<Locations> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private long  id;
    @Column(name = "ds_latitude")
    @NotBlank(message = "location.latiitude.notblank")
    private String latitude;
    @Column(name = "ds_longitude")
    @NotBlank(message = "location.longitude.notblank")
    private String longitude;
    @NotNull(message = "location.animal.notnull")
    @ManyToOne()
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
