package com.microservice.propertyservice.controller;

import com.microservice.propertyservice.model.Address;
import com.microservice.propertyservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/all-addresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/country/{country}")
    public List<Address> getAddressesFromCountry(@PathVariable("country") String country, Sort sort) {
        return addressService.getSortAddressesForCountry(country, sort.by("id").ascending());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
    }
}