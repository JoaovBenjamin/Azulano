package com.example.pokedex.model.Habitat;

import org.springframework.hateoas.EntityModel;

import com.example.pokedex.dto.Habitat.HabitatDTO;
import com.example.pokedex.model.Animals.Animals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Size(message = "{habitat.typehabitat.size}", min = 10)
    private String typeHabitat;
    @NotBlank(message = "{habitat.temperatureWater.notblank}")
    @Size(message = "{habitat.temperatureWater.size}", min = 2)
    private String temperatureWater;
    @NotBlank(message = "{habitat.phWater.notblank}")
    @Size(message = "{habitat.ph.size}", min = 2)
    private String phWater;
    @NotNull(message = "{habitat.animals.notnull}")
    @ManyToOne()
    private Animals animals;
    

    public Habitat(HabitatDTO data){
        this.animals = data.animals();
        this.phWater = data.phWater();
        this.temperatureWater = data.temperatureWater();
        this.typeHabitat = data.typeHabitat();
    }
}

