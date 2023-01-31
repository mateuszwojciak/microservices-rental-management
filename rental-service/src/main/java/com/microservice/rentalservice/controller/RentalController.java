package com.microservice.rentalservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.rentalservice.exception.InvalidInputException;
import com.microservice.rentalservice.exception.ResourceNotFoundException;
import com.microservice.rentalservice.model.Rental;
import com.microservice.rentalservice.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/all")
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/dates/{startDate}/{endDate}")
    public List<Rental> getRentalsBetweenDates(@PathVariable("startDate") LocalDate startDate,
                                               @PathVariable("endDate") LocalDate endDate) {
        return rentalService.getRentalsBetweenDates(startDate, endDate);
    }

    @GetMapping("/expiring/{numDays}")
    public List<Rental> getExpiringRentals(@PathVariable("numDays") Integer numberOfDates) {
        return rentalService.getExpiringRentals(numberOfDates);
    }

    @GetMapping("/info/{id}")
    public Rental getRentalById(@PathVariable("id") Long id) {
        Rental rental = rentalService.getRentalById(id);
        if (rental == null)
            throw new ResourceNotFoundException("Rental with id " + id + " not found.");

        return rental;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRental(@RequestBody Rental rental) throws JsonProcessingException {
        if(rental.getTenantId() == null || rental.getPropertyId() == null || rental.getPropertyType() == null
        || rental.getStartDate() == null || rental.getTotalAmount() == null)
            throw new InvalidInputException("Missing information in create request.");

        rentalService.createRental(rental);
    }

    @PutMapping("/edit/{id}")
    public void updateRental(@PathVariable("id") Long id, @RequestBody Rental rental) throws JsonProcessingException {
        if(rental.getTenantId() == null || rental.getPropertyId() == null
                || rental.getStartDate() == null || rental.getTotalAmount() == null)
            throw new InvalidInputException("Missing information in update request.");

        rentalService.updateRental(rental);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRental(@PathVariable("id") Long id) throws JsonProcessingException {
        rentalService.deleteRental(id);
    }
}
