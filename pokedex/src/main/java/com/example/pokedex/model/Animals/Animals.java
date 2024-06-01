package com.example.pokedex.model.Animals;

import org.springframework.hateoas.EntityModel;
import com.e
import com.example.pokedex.dto.Animals.AnimalsDTO;

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
    @Size(message = "{animals.family.size}", min = 10)
    private String family;
    @NotBlank(message = "{animals.diet.notblank}")
    @Size(message = "{animals.diet.size}", min = 10)
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
    
}
