package com.microservice.propertyservice.service;

import com.microservice.propertyservice.model.House;
import com.microservice.propertyservice.model.value_object.ResponseTemplateVO;
import com.microservice.propertyservice.model.value_object.Tenant;
import com.microservice.propertyservice.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HouseService {


    private final RestTemplate restTemplate;

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(RestTemplate restTemplate, HouseRepository houseRepository) {
        this.restTemplate = restTemplate;
        this.houseRepository = houseRepository;
    }

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public void createHouse(House house) {
        houseRepository.save(house);
    }

    public House getHouseById(Long houseId) {
        return houseRepository.findById(houseId).orElse(null);
    }

    public ResponseTemplateVO getHouseWithTenant(Long houseId) {
        House house = houseRepository.findById(houseId).orElse(null);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Tenant tenant = new Tenant();

        if (house != null) {
            tenant = restTemplate.getForObject(
                    "http://localhost:8082/tenants/info/" + house.getTenantId(), Tenant.class);
        }

        vo.setTenant(tenant);
        vo.setProperty(house);

        return vo;
    }

    public void updateHouse(House house) {
        houseRepository.save(house);
    }

    public void deleteHouse(Long houseId) {
        houseRepository.deleteById(houseId);
    }
}
