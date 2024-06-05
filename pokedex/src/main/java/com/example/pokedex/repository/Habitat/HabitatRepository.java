package com.example.pokedex.repository.Habitat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pokedex.model.Habitat.Habitat;



public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    
    Page<Habitat> findByNameHabitat(String nameHabitat, Pageable pageable);
}
