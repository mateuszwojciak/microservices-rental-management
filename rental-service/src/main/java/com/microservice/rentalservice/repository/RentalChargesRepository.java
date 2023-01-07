package com.microservice.rentalservice.repository;

import com.microservice.rentalservice.model.RentalCharges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalChargesRepository extends JpaRepository<RentalCharges, Long> {

    List<RentalCharges> findRentalChargesByDescription(String description);

    @Query(value = "SELECT * FROM rental_charges WHERE rental_id = :rentalId",
            nativeQuery = true)
    List<RentalCharges> findRentalChargesForRentalId(@Param("rentalId") Long rentalId);
}
