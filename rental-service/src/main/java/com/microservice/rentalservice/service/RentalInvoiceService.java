package com.microservice.rentalservice.service;

import com.microservice.rentalservice.exception.RentalNotFoundException;
import com.microservice.rentalservice.model.Rental;
import com.microservice.rentalservice.model.RentalCharges;
import com.microservice.rentalservice.model.RentalInvoice;
import com.microservice.rentalservice.repository.RentalChargesRepository;
import com.microservice.rentalservice.repository.RentalInvoiceRepository;
import com.microservice.rentalservice.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalInvoiceService {

    private final RentalInvoiceRepository rentalInvoiceRepository;
    private final RentalRepository rentalRepository;
    private final RentalChargesRepository rentalChargesRepository;

    @Autowired
    public RentalInvoiceService(RentalInvoiceRepository rentalInvoiceRepository,
                                RentalRepository rentalRepository,
                                RentalChargesRepository rentalChargesRepository) {
        this.rentalInvoiceRepository = rentalInvoiceRepository;
        this.rentalRepository = rentalRepository;
        this.rentalChargesRepository = rentalChargesRepository;
    }

    public List<RentalInvoice> getAllInvoices() {
        return rentalInvoiceRepository.findAll(Sort.by("dueDate").descending());
    }

    public List<RentalInvoice> getInvoicesForRentalId(Long rentalId) {
        return rentalInvoiceRepository.findByRentalId(rentalId);
    }

    public List<RentalInvoice> getInvoicesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return rentalInvoiceRepository.findAll().stream()
                .filter(rentalInvoice -> rentalInvoice.getDueDate().isAfter(startDate) &&
                        rentalInvoice.getDueDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public void createInvoice(Long rentalId, LocalDate invoiceDate) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() ->
                new RentalNotFoundException("Rental id not found."));
        List<RentalCharges> charges = rentalChargesRepository.findRentalChargesForRentalId(rentalId);
        Optional<BigDecimal> totalAmount = charges.stream()
                .map(RentalCharges::getAmount)
                .reduce(BigDecimal::add);

        RentalInvoice rentalInvoice = new RentalInvoice();
        rentalInvoice.setRentalId(rental.getId());
        rentalInvoice.setInvoiceDate(invoiceDate);
        rentalInvoice.setDueDate(invoiceDate.plusDays(14));
        BigDecimal invoiceAmount = calculateFinalAmount(rental.getTotalAmount(), totalAmount.orElse(BigDecimal.ZERO));
        rentalInvoice.setAmount(invoiceAmount);

        rentalInvoiceRepository.save(rentalInvoice);
    }

    public static BigDecimal calculateFinalAmount(BigDecimal rentalAmount, BigDecimal chargesAmount) {
        return rentalAmount.add(chargesAmount);
    }

    public void deleteInvoice(Long id) {
        rentalInvoiceRepository.deleteById(id);
    }
}
