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
@Table(name = "offices")
public class Office implements Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "complex_name")
    private String buildingComplexName;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST } )
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "people_capacity")
    private Integer peopleCapacity;

    @Column(name = "square_meter")
    private Integer squareMeter;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(
            cascade = { CascadeType.MERGE, CascadeType.PERSIST }
    )
    @JoinTable(
            name = "office_amenities",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;

    @Column(name = "tenant_id")
    private Long tenantId;

    public Office(Long id, String name, String buildingComplexName, Address address, Integer peopleCapacity, Integer squareMeter, Integer price, Set<Amenity> amenities, Long tenantId) {
        this.id = id;
        this.name = name;
        this.buildingComplexName = buildingComplexName;
        this.address = address;
        this.peopleCapacity = peopleCapacity;
        this.squareMeter = squareMeter;
        this.price = price;
        this.amenities = amenities;
        this.tenantId = tenantId;
    }
}
