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
@Table(name = "amenities")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "amenities")
    private Set<Apartment> apartmentSet;

    @ManyToMany(mappedBy = "amenities")
    private Set<House> houseSet;

    @ManyToMany(mappedBy = "amenities")
    private Set<Office> officeSet;

    public Amenity(Long id, String name, String description, Set<Apartment> apartmentSet, Set<House> houseSet, Set<Office> officeSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.apartmentSet = apartmentSet;
        this.houseSet = houseSet;
        this.officeSet = officeSet;
    }
}
