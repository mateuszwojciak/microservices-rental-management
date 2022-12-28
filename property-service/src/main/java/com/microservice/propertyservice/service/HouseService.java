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


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HouseRepository houseRepository;

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public void createHouse(House house) {
        houseRepository.save(house);
    }

    public ResponseTemplateVO getHouseWithTenant(Long houseId) {
        House house = houseRepository.findById(houseId).orElse(null);
        ResponseTemplateVO vo = new ResponseTemplateVO();

        Tenant tenant = restTemplate.getForObject("http://localhost:8082/tenants/"
                + house.getTenantId(), Tenant.class);

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