package com.microservice.rentalservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rental_charges")
public class RentalCharges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "description")
    private String description;

    @Column(name = "charge_amount")
    private BigDecimal amount;

    public RentalCharges(Long id, Rental rental, String description, BigDecimal amount) {
        this.id = id;
        this.rental = rental;
        this.description = description;
        this.amount = amount;
    }
}
