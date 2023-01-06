package com.microservice.rentalservice.repository;

import com.microservice.rentalservice.model.RentalInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalInvoiceRepository extends JpaRepository<RentalInvoice, Long> {
    List<RentalInvoice> findByRentalId(Long id);

}
