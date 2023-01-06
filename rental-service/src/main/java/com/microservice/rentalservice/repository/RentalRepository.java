package com.microservice.rentalservice.repository;

import com.microservice.rentalservice.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(value = "SELECT r FROM rentals r WHERE r.end_date BETWEEN CURRENT_DATE and CURRENT_DATE + :numberOfDays",
    nativeQuery = true)
    List<Rental> getExpiringRentals(@Param("numberOfDays") Integer numberOfDates);
}
