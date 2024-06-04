package com.example.pokedex.dto.Animals;

import org.springframework.hateoas.EntityModel;

import com.example.pokedex.controller.Animals.AnimalsController;
import com.example.pokedex.model.Animals.Animals;
import com.example.pokedex.model.Habitat.Habitat;

public record AnimalsDTO(
    String name,
    String species,
    String family,
    String diet,
    Habitat habitat
) {
   
}

