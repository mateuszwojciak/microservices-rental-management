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

    public List<Address> getAddressesForCountry(String country) {
        return addressRepository.findAllByCountry(country, Sort.by("city").ascending());
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}