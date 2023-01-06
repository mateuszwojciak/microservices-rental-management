package com.microservice.rentalservice.controller;

import com.microservice.rentalservice.model.RentalInvoice;
import com.microservice.rentalservice.service.RentalInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class RentalInvoiceController {

    private final RentalInvoiceService rentalInvoiceService;

    @Autowired
    public RentalInvoiceController(RentalInvoiceService rentalInvoiceService) {
        this.rentalInvoiceService = rentalInvoiceService;
    }

    @GetMapping("/all")
    public List<RentalInvoice> getAllInvoices() {
        return rentalInvoiceService.getAllInvoices();
    }

    @GetMapping("/rental/{id}")
    public List<RentalInvoice> getInvoicesForRentalId(@PathVariable("id") Long id) {
        return rentalInvoiceService.getInvoicesForRentalId(id);
    }

    @GetMapping("/dates/{startDate}-{endDate}")
    public List<RentalInvoice> getInvoicesBetweenDates(@PathVariable("startDate") LocalDate startDate,
                                                       @PathVariable("endDate") LocalDate endDate) {
        return rentalInvoiceService.getInvoicesBetweenDates(startDate, endDate);
    }

    @PostMapping("/create/{rentalId}/date/{invoiceDate}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInvoice(@PathVariable("rentalId") Long rentalId, @PathVariable("invoiceDate") LocalDate invoiceDate) {
        rentalInvoiceService.createInvoice(rentalId, invoiceDate);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable("id") Long id) {
        rentalInvoiceService.deleteInvoice(id);
    }
}
