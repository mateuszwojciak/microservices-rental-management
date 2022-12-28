package com.microservice.tenantservice.service;

import com.microservice.tenantservice.model.Tenant;
import com.microservice.tenantservice.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public Tenant saveTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public Optional<Tenant> getTenant(Long tenantId) {
        return tenantRepository.findById(tenantId);
    }
}
