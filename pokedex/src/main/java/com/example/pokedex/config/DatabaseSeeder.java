package com.example.pokedex.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.pokedex.model.Animals.Animals;
import com.example.pokedex.model.Habitat.Habitat;
import com.example.pokedex.model.Locations.Locations;
import com.example.pokedex.repository.Animals.AnimalsRepository;
import com.example.pokedex.repository.Habitat.HabitatRepository;
import com.example.pokedex.repository.Locations.LocationsRepository;

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
                  .phWater("8.1")
                  .temperatureWater("26")
                  .typeHabitat("Ambiente Marinho Tropical")
                  .build(),
          Habitat
                  .builder()
                  .id(2L)
                  .nameHabitat( "Costa da Califórnia")
                  .phWater("8.0")
                  .temperatureWater("15")
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
                   .species("Tursiops truncatus")
                   .build(),
            Animals
                   .builder()
                   .id(2L)
                   .name("Golfinho Teste")
                   .diet("Peixes pequenos")
                   .habitat(habitatRepository.findById(2L).get())
                   .family("Delphinidae")
                   .species("Tursiops truncatus")
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
