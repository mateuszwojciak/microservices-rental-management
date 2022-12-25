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
@Table(name = "houses")
public class House implements Property {

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

    @Column(name = "number_garages")
    private Integer numberOfGarages;

    @Column(name = "square_meter")
    private Integer squareMeter;

    @Column(name = "price")
    private Integer price;

    @ManyToMany
    @JoinTable(name = "house_amenities",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;

    public House(Long id, String name, Address address, Integer numberOfBedrooms, Integer numberOfBathrooms, Integer numberOfGarages, Integer squareMeter, Integer price, Set<Amenity> amenities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfGarages = numberOfGarages;
        this.squareMeter = squareMeter;
        this.price = price;
        this.amenities = amenities;
    }
}
