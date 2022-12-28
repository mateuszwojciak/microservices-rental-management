package com.microservice.tenantservice.service;

import com.microservice.tenantservice.model.Tenant;
import com.microservice.tenantservice.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(Long tenantId) {
        return tenantRepository.findById(tenantId).orElse(null);
    }

    public List<Tenant> getTenantByName(String name) {
        return tenantRepository.findAllByFirstName(name);
    }

    public void createTenant(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    public void updateTenant(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    public void deleteTenant(Long tenantId) {
        tenantRepository.deleteById(tenantId);
    }
}
