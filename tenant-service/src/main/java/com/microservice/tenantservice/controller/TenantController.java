package com.microservice.tenantservice.controller;

import com.microservice.tenantservice.model.Tenant;
import com.microservice.tenantservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/")
    public Tenant saveTenant(@RequestBody Tenant tenant) {
        return tenantService.saveTenant(tenant);
    }

    @GetMapping("/{id}")
    public Optional<Tenant> getTenantById(@PathVariable("id") Long tenantId) {
        return tenantService.getTenant(tenantId);
    }
}
