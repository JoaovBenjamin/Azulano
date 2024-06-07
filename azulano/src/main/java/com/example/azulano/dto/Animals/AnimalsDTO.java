package com.example.azulano.dto.Animals;


import com.example.azulano.model.Habitat.Habitat;

public record AnimalsDTO(
    String name,
    String family,
    String diet,
    Habitat habitat
) {
   
}

