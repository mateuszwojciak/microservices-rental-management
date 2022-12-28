package com.microservice.propertyservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne(mappedBy = "address")
    @JsonBackReference(value = "apartment-address")
    private Apartment apartment;

    @OneToOne(mappedBy = "address")
    @JsonBackReference(value = "house-address")
    private House house;

    @OneToOne(mappedBy = "address")
    @JsonBackReference(value = "office-address")
    private Office office;

    public Address(Long id, String streetAddress, String city, String country, Apartment apartment, House house, Office office) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
        this.apartment = apartment;
        this.house = house;
        this.office = office;
    }
}