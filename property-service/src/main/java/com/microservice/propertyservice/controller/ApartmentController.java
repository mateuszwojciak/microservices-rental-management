package com.microservice.propertyservice.controller;

import com.microservice.propertyservice.exception.InvalidInputException;
import com.microservice.propertyservice.exception.ResourceNotFoundException;
import com.microservice.propertyservice.model.Apartment;
import com.microservice.propertyservice.model.value_object.ResponseTemplateVO;
import com.microservice.propertyservice.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/list-of-apartments")
    public List<Apartment> getAllApartments() {
        return apartmentService.getAllApartments();
    }

    @GetMapping("/info/{id}")
    public ResponseTemplateVO getApartmentWithTenant(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ResponseTemplateVO apartment = apartmentService.getApartmentWithTenant(id);
        if (apartment == null) throw new ResourceNotFoundException (
                "Apartment with id " + id + " not found."
        );
        return apartment;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createApartment(@RequestBody Apartment apartment) throws InvalidInputException {
        if (apartment.getName() == null || apartment.getFloor() == null
        || apartment.getNumberOfRooms() == null || apartment.getSquareMeter() == null) {
            throw new InvalidInputException("Name, floor, number of rooms and square meters are required fields.");
        }
        apartmentService.createApartment(apartment);
    }

    @PutMapping("/edit/{id}")
    public void updateApartment(@PathVariable("id") Long id, @RequestBody Apartment apartment) throws InvalidInputException {
        apartment.setId(id);
        if (apartment.getName() == null || apartment.getFloor() == null
                || apartment.getNumberOfRooms() == null || apartment.getSquareMeter() == null) {
            throw new InvalidInputException("Name, floor, number of bedrooms and square meters are required fields.");
        }
        apartmentService.updateApartment(apartment);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApartment(@PathVariable("id") Long id) {
        apartmentService.deleteApartment(id);
    }
}
