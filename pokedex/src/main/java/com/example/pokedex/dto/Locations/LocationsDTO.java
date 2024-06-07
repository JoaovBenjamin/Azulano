package com.example.pokedex.dto.Locations;

import com.example.pokedex.model.Animals.Animals;

public record LocationsDTO(
    String latitude,
    String longitude,
    Animals animals
) {
    
}
