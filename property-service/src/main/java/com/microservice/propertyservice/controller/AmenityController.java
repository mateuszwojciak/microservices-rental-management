package com.microservice.propertyservice.controller;

import com.microservice.propertyservice.exception.InvalidInputException;
import com.microservice.propertyservice.exception.ResourceNotFoundException;
import com.microservice.propertyservice.model.Amenity;
import com.microservice.propertyservice.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenityController {

    private final AmenityService amenityService;

    @Autowired
    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping("/all")
    public List<Amenity> getAllAmenities() {
        return amenityService.getAllAmenities();
    }

    @GetMapping("/info/{name}")
    public Amenity getAmenityByName(@PathVariable("name") String name) throws ResourceNotFoundException {
        Amenity amenity = amenityService.getAmenityByName(name);
        if (amenity == null) throw new ResourceNotFoundException (
                "Amenity with name '" + name + "' not found."
        );
        return amenity;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAmenity(@RequestBody Amenity amenity) throws InvalidInputException {
        if (amenity.getName() == null) {
            throw new InvalidInputException("Name is a required field.");
        }
        amenityService.createAmenity(amenity);
    }

    @PutMapping("/edit/{id}")
    public void updateAmenity(@PathVariable("id") Long id, @RequestBody Amenity amenity) throws InvalidInputException {
        amenity.setId(id);
        if (amenity.getName() == null) {
            throw new InvalidInputException("Name is a required field.");
        }
        amenityService.updateAmenity(amenity);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAmenity(@PathVariable("id") Long id) {
        amenityService.deleteAmenity(id);
    }
}
