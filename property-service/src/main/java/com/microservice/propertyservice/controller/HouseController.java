package com.microservice.propertyservice.controller;

import com.microservice.propertyservice.exception.InvalidInputException;
import com.microservice.propertyservice.exception.ResourceNotFoundException;
import com.microservice.propertyservice.model.House;
import com.microservice.propertyservice.model.value_object.ResponseTemplateVO;
import com.microservice.propertyservice.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/all")
    public List<House> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/info/{id}")
    public ResponseTemplateVO getHouseWithTenant(@PathVariable("id") Long id) {
        ResponseTemplateVO house = houseService.getHouseWithTenant(id);
        if (house.getProperty() == null)
            throw new ResourceNotFoundException("House with id " + id + " not found.");

        return house;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHouse(@RequestBody House house) {
        if (house.getName() == null || house.getNumberOfRooms() == null || house.getSquareMeter() == null) {
            throw new InvalidInputException("Name, number of rooms and square meters are required fields.");
        }
        houseService.createHouse(house);
    }

    @PutMapping("/edit/{id}")
    public void updateHouse(@PathVariable("id") Long id, @RequestBody House house) {
        house.setId(id);
        if (house.getName() == null || house.getNumberOfRooms() == null || house.getSquareMeter() == null) {
            throw new InvalidInputException("Name, number of rooms and square meters are required fields.");
        }
        houseService.updateHouse(house);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouse(@PathVariable("id") Long id) {
        houseService.deleteHouse(id);
    }
}
