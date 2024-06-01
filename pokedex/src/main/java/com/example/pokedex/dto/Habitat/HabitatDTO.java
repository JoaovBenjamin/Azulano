package com.example.pokedex.dto.Habitat;

import com.example.pokedex.model.Animals.Animals;

public record HabitatDTO(
    String typeHabitat,
    String phWater,
    String temperatureWater,
    Animals animals
) {
    
}
