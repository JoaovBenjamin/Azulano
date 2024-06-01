package com.example.pokedex.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.pokedex.model.Animals.Animals;
import com.example.pokedex.repository.Animals.AnimalsRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    AnimalsRepository animalsRepository;

    @Override
    public void run(String... args) throws Exception {
        animalsRepository.saveAll(
          List.of(
            Animals
                   .builder()
                   .id(1L)
                   .diet("Peixes pequenos")
                   .family("Delphinidae")
                   .species("Tursiops truncatus")
                   .build()

          )  
        );

        
    }
    
}
