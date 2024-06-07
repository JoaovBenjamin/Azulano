package com.example.azulano.repository.Locations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.azulano.model.Locations.Locations;



public interface LocationsRepository  extends JpaRepository<Locations, Long>{
    
    Page<Locations> findByLongitude(String longitude, Pageable pageable);
    Page<Locations> findByLatitude(String latitude, Pageable pageable);

    @Query("SELECT v FROM Locations v WHERE v.latitude = :latitude AND v.longitude = :longitude")
    Page<Locations> findByLatitudeAndLongitude(String latitude, String longitude, Pageable pageable);
}
