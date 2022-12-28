package com.microservice.tenantservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tenants")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("person")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

   // @OneToMany(mappedBy = "tenant")
   // private List<Rental> rentals;
}
