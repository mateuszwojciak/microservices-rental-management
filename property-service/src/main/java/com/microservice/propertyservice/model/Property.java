package com.microservice.propertyservice.model;

import com.microservice.propertyservice.model.value_object.Tenant;

import java.util.Set;

public interface Property {
    Long getId();
    String getName();
    Address getAddress();
    Integer getPrice();
    Set<Amenity> getAmenities();
    //Long getTenantId();
}
