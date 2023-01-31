package com.microservice.propertyservice.service;

import com.microservice.propertyservice.model.Office;
import com.microservice.propertyservice.model.value_object.ResponseTemplateVO;
import com.microservice.propertyservice.model.value_object.Tenant;
import com.microservice.propertyservice.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OfficeService {

    private final RestTemplate restTemplate;

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeService(RestTemplate restTemplate, OfficeRepository officeRepository) {
        this.restTemplate = restTemplate;
        this.officeRepository = officeRepository;
    }

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    public void createOffice(Office office) {
        officeRepository.save(office);
    }

    public Office getOfficeById(Long officeId) {
        return officeRepository.findById(officeId).orElse(null);
    }

    public ResponseTemplateVO getOfficeWithTenant(Long officeId) {
        Office office = officeRepository.findById(officeId).orElse(null);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Tenant tenant = new Tenant();

        if (office != null) {
            tenant = restTemplate.getForObject(
                    "http://localhost:8082/tenants/info/" + office.getTenantId(), Tenant.class);
        }

        vo.setTenant(tenant);
        vo.setProperty(office);

        return vo;
    }

    public void updateOffice(Office office) {
        officeRepository.save(office);
    }

    public void deleteOffice(Long officeId) {
        officeRepository.deleteById(officeId);
    }
}
