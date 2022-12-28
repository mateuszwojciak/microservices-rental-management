package com.microservice.tenantservice.controller;

import com.microservice.tenantservice.exception.InvalidInputException;
import com.microservice.tenantservice.exception.ResourceNotFoundException;
import com.microservice.tenantservice.model.Tenant;
import com.microservice.tenantservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping("/all")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/info/{id}")
    public Tenant getTenantById(@PathVariable("id") Long tenantId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        if (tenant == null)
            throw new ResourceNotFoundException("Tenant with id " + tenantId + " not found.");

        return tenantService.getTenantById(tenantId);
    }

    @GetMapping("/name/{name}")
    public List<Tenant> getTenantByName(@PathVariable("name") String name) {
        return tenantService.getTenantByName(name);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTenant(@RequestBody Tenant tenant) throws InvalidInputException {
        if (tenant.getIdentificationNumber() == null || tenant.getIdentificationNumber().toCharArray().length <= 7)
            throw new InvalidInputException("Identification number is a required field.");

        tenantService.createTenant(tenant);
    }

    @PutMapping("/edit/{id}")
    public void updateTenant(@PathVariable("id") Long id, @RequestBody Tenant tenant) throws InvalidInputException {
        tenant.setId(id);
        if (tenant.getIdentificationNumber() == null || tenant.getIdentificationNumber().toCharArray().length <= 7)
            throw new InvalidInputException("Identification number is a required field.");

        tenantService.updateTenant(tenant);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTenant(@PathVariable("id") Long id) {
        tenantService.deleteTenant(id);
    }
}