package com.microservice.propertyservice.repository;

import com.microservice.propertyservice.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {

}
