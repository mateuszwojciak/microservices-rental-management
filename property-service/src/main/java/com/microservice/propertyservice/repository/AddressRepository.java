package com.microservice.propertyservice.repository;

import com.microservice.propertyservice.model.Address;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByCountry(String country, Sort sort);
}
