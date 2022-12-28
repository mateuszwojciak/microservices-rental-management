package com.microservice.propertyservice.repository;

import com.microservice.propertyservice.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {

}
