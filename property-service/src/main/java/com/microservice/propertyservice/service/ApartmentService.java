package com.microservice.propertyservice.service;

import com.microservice.propertyservice.model.Apartment;
import com.microservice.propertyservice.model.value_object.ResponseTemplateVO;
import com.microservice.propertyservice.model.value_object.Tenant;
import com.microservice.propertyservice.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApartmentService {

    private final RestTemplate restTemplate;

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(RestTemplate restTemplate, ApartmentRepository apartmentRepository) {
        this.restTemplate = restTemplate;
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    public void createApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    public Apartment getApartmentById(Long apartmentId) {
        return apartmentRepository.findById(apartmentId).orElse(null);
    }

    public ResponseTemplateVO getApartmentWithTenant(Long apartmentId) {
        Apartment apartment = apartmentRepository.findById(apartmentId).orElse(null);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Tenant tenant = new Tenant();

        if (apartment != null) {
            tenant = restTemplate.getForObject(
                    "http://localhost:8082/tenants/info/" + apartment.getTenantId(), Tenant.class);
        }

        vo.setTenant(tenant);
        vo.setProperty(apartment);

        return vo;
    }

    public void updateApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    public void deleteApartment(Long apartmentId) {
        apartmentRepository.deleteById(apartmentId);
    }
}
