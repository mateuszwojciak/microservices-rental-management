package com.microservice.propertyservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.propertyservice.model.Apartment;
import com.microservice.propertyservice.model.House;
import com.microservice.propertyservice.model.Office;
import com.microservice.propertyservice.service.ApartmentService;
import com.microservice.propertyservice.service.HouseService;
import com.microservice.propertyservice.service.OfficeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private static final String createTopic = "${topic.name.create}";
    private static final String deleteTopic = "${topic.name.delete}";

    private final ObjectMapper objectMapper;
    private final ApartmentService apartmentService;
    private final HouseService houseService;
    private final OfficeService officeService;

    @Autowired
    public KafkaConsumer(ObjectMapper objectMapper, ApartmentService apartmentService, HouseService houseService, OfficeService officeService) {
        this.objectMapper = objectMapper;
        this.apartmentService = apartmentService;
        this.houseService = houseService;
        this.officeService = officeService;
    }

    @KafkaListener(topics = createTopic)
    public void consumeCreateMessage(String message) throws JsonProcessingException {
        log.info("Message for new rental consumed {}", message);
        Rental rental = objectMapper.readValue(message, Rental.class);

        if (rental.getPropertyType() == "APARTMENT") {
            Apartment apartment = apartmentService.getApartmentById(rental.getId());
            apartment.setTenantId(rental.getTenantId());

            apartmentService.updateApartment(apartment);
            log.info("Tenant id for apartment updated.");
        } else if (rental.getPropertyType() == "OFFICE") {
            Office office = officeService.getOfficeById(rental.getId());
            office.setTenantId(rental.getTenantId());

            officeService.updateOffice(office);
            log.info("Tenant id for office updated.");
        } else {
            House house = houseService.getHouseById(rental.getId());
            house.setTenantId(rental.getTenantId());

            houseService.updateHouse(house);
            log.info("Tenant id for house updated.");
        }
    }

    @KafkaListener(topics = deleteTopic)
    public void consumeDeleteTenantIdMessage(String message) throws JsonProcessingException {
        log.info("Message for delete tenant id consumed {}", message);
        Rental rental = objectMapper.readValue(message, Rental.class);

        if (rental.getPropertyType() == "APARTMENT") {
            Apartment apartment = apartmentService.getApartmentById(rental.getId());
            apartment.setTenantId(null);

            apartmentService.updateApartment(apartment);
            log.info("Tenant id for apartment cleared.");
        } else if (rental.getPropertyType() == "OFFICE") {
            Office office = officeService.getOfficeById(rental.getId());
            office.setTenantId(null);

            officeService.updateOffice(office);
            log.info("Tenant id for office cleared.");
        } else {
            House house = houseService.getHouseById(rental.getId());
            house.setTenantId(null);

            houseService.updateHouse(house);
            log.info("Tenant id for house cleared.");
        }
    }
}
