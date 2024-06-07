package com.example.pokedex.dto.Animals;


import com.example.pokedex.model.Habitat.Habitat;

public record AnimalsDTO(
    String name,
    String family,
    String diet,
    Habitat habitat
) {
   
}

