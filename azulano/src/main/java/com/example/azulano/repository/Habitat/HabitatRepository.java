package com.example.azulano.repository.Habitat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.azulano.model.Habitat.Habitat;



public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    
    Page<Habitat> findByNameHabitat(String nameHabitat, Pageable pageable);
}
