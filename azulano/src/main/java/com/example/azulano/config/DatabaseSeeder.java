package com.example.azulano.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.azulano.model.Animals.Animals;
import com.example.azulano.model.Habitat.Habitat;
import com.example.azulano.model.Locations.Locations;
import com.example.azulano.repository.Animals.AnimalsRepository;
import com.example.azulano.repository.Habitat.HabitatRepository;
import com.example.azulano.repository.Locations.LocationsRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    HabitatRepository habitatRepository;

    @Autowired LocationsRepository locationsRepository;

    @Override
    public void run(String... args) throws Exception {
        
      habitatRepository.saveAll(
        List.of(
          Habitat
                  .builder()
                  .id(1L)
                  .nameHabitat("Recifes de Coral do Pacífico")
                  .typeHabitat("Ambiente Marinho Tropical")
                  .build(),
          Habitat
                  .builder()
                  .id(2L)
                  .nameHabitat( "Costa da Califórnia")
                  .typeHabitat( "Ambiente Marinho Temperado")
                  .build()
        )
      );
      
      animalsRepository.saveAll(
          List.of(
            Animals
                   .builder()
                   .id(1L)
                   .name("Golfinho")
                   .diet("Peixes pequenos")
                   .habitat(habitatRepository.findById(1L).get())
                   .family("Delphinidae")
                   .build(),
            Animals
                   .builder()
                   .id(2L)
                   .name("Golfinho Teste")
                   .diet("Peixes pequenos")
                   .habitat(habitatRepository.findById(2L).get())
                   .family("Delphinidae")
                   .build()

          )  
        );

        locationsRepository.saveAll(
          List.of(
            Locations
                    .builder()
                    .animal(animalsRepository.findById(1L).get())
                    .latitude("10*20*23")
                    .longitude("10*23*01")
                    .build(),
          
            Locations
                    .builder()
                    .animal(animalsRepository.findById(2L).get())
                    .latitude("10*20*23")
                    .longitude("10*23*01")
                    .build()
        
        )
      );
    }
    
}
