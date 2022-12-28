package com.microservice.propertyservice.repository;

import com.microservice.propertyservice.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
