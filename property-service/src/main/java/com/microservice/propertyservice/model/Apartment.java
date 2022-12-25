package com.microservice.propertyservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment implements Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "number_bedrooms")
    private Integer numberOfBedrooms;

    @Column(name = "number_bathrooms")
    private Integer numberOfBathrooms;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "square_meter")
    private Integer squareMeter;

    @Column(name = "price")
    private Integer price;

    @ManyToMany
    @JoinTable(name = "apartment_amenities",
    joinColumns = @JoinColumn(name = "apartment_id"),
    inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;

    public Apartment(Long id, String name, Address address, Integer numberOfBedrooms, Integer numberOfBathrooms, Integer floor, Integer squareMeter, Integer price, Set<Amenity> amenities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.floor = floor;
        this.squareMeter = squareMeter;
        this.price = price;
        this.amenities = amenities;
    }
}
