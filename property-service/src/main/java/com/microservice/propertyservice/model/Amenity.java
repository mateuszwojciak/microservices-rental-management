package com.microservice.propertyservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    private Set<Apartment> apartmentSet;

    @ManyToMany(mappedBy = "amenities")
    @JsonIgnore
    private Set<House> houseSet;

    @ManyToMany(mappedBy = "amenities")
    @JsonIgnore
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
