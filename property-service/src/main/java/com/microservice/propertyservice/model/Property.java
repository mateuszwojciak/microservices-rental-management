package com.microservice.propertyservice.model;

import java.util.Set;

public interface Property {
    Long getId();
    String getName();
    Address getAddress();
    Integer getPrice();
    Set<Amenity> getAmenities();
}
