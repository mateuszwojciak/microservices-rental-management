package com.microservice.rentalservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rental_invoices")
public class RentalInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "invoice_due_date")
    private LocalDate dueDate;

    @Column(name = "final_amount")
    private BigDecimal amount;

    public RentalInvoice(Long id, Rental rental, LocalDate invoiceDate, LocalDate dueDate, BigDecimal amount) {
        this.id = id;
        this.rental = rental;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.amount = amount;
    }
}
