package com.example.azulano.model.Animals;

import org.springframework.hateoas.EntityModel;

import com.example.azulano.controller.Animals.AnimalsController;
import com.example.azulano.dto.Animals.AnimalsDTO;
import com.example.azulano.model.Habitat.Habitat;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "T_AZL_SPECIES")
public class Animals extends EntityModel<Animals>  {
    @Column(name = "id_species")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_comum")
    @NotBlank(message = "{animals.name.notblank}")
    @Size(min = 3, message = "{animals.name.size}")
    private String name;
    @Column(name = "nm_cientifico")
    @NotBlank(message = "{animals.family.notblank}")
    @Size(message = "{animals.family.size}", min = 5)
    private String family;
    @Column(name = "tx_descricao")
    @NotBlank(message = "{animals.diet.notblank}")
    @Size(message = "{animals.diet.size}", min = 5)
    private String diet;
    @ManyToOne()
    @NotNull(message = "{animals.habitat.notnull}")
    private Habitat habitat;    

    public Animals (AnimalsDTO data){
        this.diet = data.diet();
        this.habitat =  data.habitat();
        this.family = data.family();
        this.name = data.name();
    }

    public EntityModel<Animals> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(AnimalsController.class).searchById(id)).withSelfRel(),
            linkTo(methodOn(AnimalsController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(AnimalsController.class).findByPages(null, null, null)).withRel("contents")
           
        );
    }
}
