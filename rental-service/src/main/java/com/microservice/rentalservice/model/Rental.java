package com.microservice.rentalservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "rental")
    @JsonManagedReference
    private Set<RentalCharges> charges;

    @Column(name = "property_type")
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    public Rental(Long id, Long propertyId, Long tenantId, LocalDate startDate, LocalDate endDate, BigDecimal totalAmount, Set<RentalCharges> charges, PropertyType propertyType) {
        this.id = id;
        this.propertyId = propertyId;
        this.tenantId = tenantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
        this.charges = charges;
        this.propertyType = propertyType;
    }
}