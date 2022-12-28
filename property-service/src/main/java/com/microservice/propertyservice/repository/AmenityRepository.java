package com.microservice.propertyservice.repository;

import com.microservice.propertyservice.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    Amenity findByName(String name);
}
