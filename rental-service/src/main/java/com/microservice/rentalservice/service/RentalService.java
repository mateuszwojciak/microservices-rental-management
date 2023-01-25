package com.microservice.rentalservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.rentalservice.kafka.KafkaProducer;
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
    private final KafkaProducer kafkaProducer;

    @Autowired
    public RentalService(RentalRepository rentalRepository, KafkaProducer kafkaProducer) {
        this.rentalRepository = rentalRepository;
        this.kafkaProducer = kafkaProducer;
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

    public void createRental(Rental rental) throws JsonProcessingException {
        rentalRepository.save(rental);
        kafkaProducer.sendMessageCreateRental(rental);
    }

    public void updateRental(Rental rental) throws JsonProcessingException {
        rentalRepository.save(rental);
        kafkaProducer.sendMessageUpdateRental(rental);
    }

    public void deleteRental(Long id) throws JsonProcessingException {
        Rental rental = rentalRepository.findById(id).orElse(null);
        kafkaProducer.sendMessageDeleteRental(rental);
        rentalRepository.deleteById(id);
    }
}
