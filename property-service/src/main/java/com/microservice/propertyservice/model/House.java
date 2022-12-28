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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST } )
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "number_rooms")
    private Integer numberOfRooms;

    @Column(name = "number_bathrooms")
    private Integer numberOfBathrooms;

    @Column(name = "number_garages")
    private Integer numberOfGarages;

    @Column(name = "square_meter")
    private Integer squareMeter;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(
            cascade = { CascadeType.MERGE, CascadeType.PERSIST }
    )
    @JoinTable(
            name = "house_amenities",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;

    @Column(name = "tenant_id")
    private Long tenantId;

    public House(Long id, String name, Address address, Integer numberOfRooms, Integer numberOfBathrooms, Integer numberOfGarages, Integer squareMeter, Integer price, Set<Amenity> amenities, Long tenantId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfGarages = numberOfGarages;
        this.squareMeter = squareMeter;
        this.price = price;
        this.amenities = amenities;
        this.tenantId = tenantId;
    }
}
