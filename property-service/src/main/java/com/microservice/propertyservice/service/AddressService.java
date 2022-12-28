package com.microservice.propertyservice.service;

import com.microservice.propertyservice.model.Address;
import com.microservice.propertyservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public List<Address> getSortAddressesForCountry(String country, Sort sort) {
        return addressRepository.findAllByCountry(country, sort.by("id").ascending());
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}