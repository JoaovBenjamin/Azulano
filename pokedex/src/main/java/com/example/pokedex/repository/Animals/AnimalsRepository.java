package com.example.pokedex.repository.Animals;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pokedex.model.Animals.Animals;

public interface AnimalsRepository extends JpaRepository<Animals, Long>{
    
}
