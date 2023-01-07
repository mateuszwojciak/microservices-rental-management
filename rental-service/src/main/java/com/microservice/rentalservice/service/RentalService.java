package com.microservice.rentalservice.service;

import com.microservice.rentalservice.model.Rental;
import com.microservice.rentalservice.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll(Sort.by("startDate").ascending());
    }

    public List<Rental> getRentalsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getEndDate().isAfter(startDate) &&
                                rental.getEndDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public List<Rental> getExpiringRentals(Integer numberOfDates) {
        return rentalRepository.getExpiringRentals(numberOfDates);
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public void createRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
