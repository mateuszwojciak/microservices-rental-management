package com.microservice.rentalservice.repository;

import com.microservice.rentalservice.model.RentalInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RentalInvoiceRepository extends JpaRepository<RentalInvoice, Long> {

    @Query(value = "SELECT * FROM rental_invoices WHERE rental_id = :rentalId", nativeQuery = true)
    List<RentalInvoice> findByRentalId(@Param("rentalId") Long id);
}
