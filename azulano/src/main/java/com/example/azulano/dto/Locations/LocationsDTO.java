package com.example.azulano.dto.Locations;

import com.example.azulano.model.Animals.Animals;

public record LocationsDTO(
    String latitude,
    String longitude,
    Animals animals
) {
    
}
