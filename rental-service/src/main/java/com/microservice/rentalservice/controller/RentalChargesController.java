package com.microservice.rentalservice.controller;

import com.microservice.rentalservice.exception.InvalidInputException;
import com.microservice.rentalservice.model.RentalCharges;
import com.microservice.rentalservice.service.RentalChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charges")
public class RentalChargesController {

    private final RentalChargesService rentalChargesService;

    @Autowired
    public RentalChargesController(RentalChargesService rentalChargesService) {
        this.rentalChargesService = rentalChargesService;
    }

    @GetMapping("/all")
    public List<RentalCharges> getAllRentalCharges() {
        return rentalChargesService.getAllRentalCharges();
    }

    @GetMapping("/info/rental/{id}")
    public List<RentalCharges> getRentalChargesForRentalId(@PathVariable("id") Long id) {
        return rentalChargesService.getRentalChargesForRentalId(id);
    }

    @GetMapping("/info/description/{desc}")
    public List<RentalCharges> getRentalChargesByDescription(@PathVariable("desc") String description) {
        return rentalChargesService.getRentalChargesByDescription(description);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRentalCharge(@RequestBody RentalCharges rentalCharges) {
        if(rentalCharges.getRental() == null || rentalCharges.getDescription() == null
        || rentalCharges.getAmount() == null)
            throw new InvalidInputException("Missing information in create request.");

        rentalChargesService.createRentalCharge(rentalCharges);
    }

    @PutMapping("/edit/{id}")
    public void updateRentalCharge(@PathVariable("id") Long id, @RequestBody RentalCharges rentalCharges) {
        if(rentalCharges.getRental() == null || rentalCharges.getDescription() == null
                || rentalCharges.getAmount() == null)
            throw new InvalidInputException("Missing information in update request.");

        rentalChargesService.updateRentalCharge(rentalCharges);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRentalCharge(@PathVariable("id") Long id) {
        rentalChargesService.deleteRentalCharge(id);
    }
}
