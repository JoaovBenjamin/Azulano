package com.example.pokedex.repository.Animals;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pokedex.model.Animals.Animals;


public interface AnimalsRepository extends JpaRepository<Animals, Long>{
    
    Page<Animals> findByName(String name, Pageable pageable);

    Page<Animals>findByFamily(String family, Pageable pageable);

    @Query("SELECT v FROM Animals v WHERE v.name = :name AND v.family = :family")
    Page<Animals> findByNameAndFamily(String name, String family, Pageable pageable);
}
