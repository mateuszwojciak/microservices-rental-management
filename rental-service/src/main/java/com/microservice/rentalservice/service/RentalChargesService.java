package com.microservice.rentalservice.service;

import com.microservice.rentalservice.model.RentalCharges;
import com.microservice.rentalservice.repository.RentalChargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalChargesService {

    private final RentalChargesRepository rentalChargesRepository;

    @Autowired
    public RentalChargesService(RentalChargesRepository rentalChargesRepository) {
        this.rentalChargesRepository = rentalChargesRepository;
    }

    public List<RentalCharges> getAllRentalCharges() {
        return rentalChargesRepository.findAll();
    }

    public List<RentalCharges> getRentalChargesForRentalId(Long rentalId) {
        return rentalChargesRepository.findRentalChargesForRentalId(rentalId);
    }

    public List<RentalCharges> getRentalChargesByDescription(String description) {
        return rentalChargesRepository.findRentalChargesByDescription(description);
    }

    public void createRentalCharge(RentalCharges rentalCharges) {
        rentalChargesRepository.save(rentalCharges);
    }

    public void updateRentalCharge(RentalCharges rentalCharges) {
        rentalChargesRepository.save(rentalCharges);
    }

    public void deleteRentalCharge(Long id) {
        rentalChargesRepository.deleteById(id);
    }
}
