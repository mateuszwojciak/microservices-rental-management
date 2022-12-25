package com.microservice.propertyservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    public Address(Long id, String streetAddress, String city, String country) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }
}
