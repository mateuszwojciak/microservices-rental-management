package com.microservice.propertyservice.service;

import com.microservice.propertyservice.model.Amenity;
import com.microservice.propertyservice.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityService {

    private final AmenityRepository amenityRepository;

    @Autowired
    public AmenityService(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    public Amenity getAmenityByName(String name) {
        return amenityRepository.findByName(name);
    }

    public void createAmenity(Amenity amenity) {
        amenityRepository.save(amenity);
    }

    public void updateAmenity(Amenity amenity) {
        amenityRepository.save(amenity);
    }

    public void deleteAmenity(Long amenityId) {
        amenityRepository.deleteById(amenityId);
    }
}
